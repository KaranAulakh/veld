package com.craftinginterpreters.veld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.craftinginterpreters.veld.TokenType.*;

class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String source) {
        this.source = source;
    }

    /**
     * SCANNER CORE
     */
    List<Token> scanTokens() {
        while (!isAtEnd()) {
            // We are at the beginning of the next lexeme.
            start = current;
            scanToken();
        }

        tokens.add(new Token(TokenType.EOF, "", null, line));
        return tokens;
    }
    private void scanToken() {
        char c = advance();
        switch (c) {
        case '(': addToken(TokenType.LEFT_PAREN); break;
        case ')': addToken(TokenType.RIGHT_PAREN); break;
        case '{': addToken(TokenType.LEFT_BRACE); break;
        case '}': addToken(TokenType.RIGHT_BRACE); break;
        case ',': addToken(TokenType.COMMA); break;
        case '.': addToken(TokenType.DOT); break;
        case '-': addToken(TokenType.MINUS); break;
        case '+': addToken(TokenType.PLUS); break;
        case ';': addToken(TokenType.SEMICOLON); break;
        case '*': addToken(TokenType.STAR); break;
        case '!':
            addToken(match('=') ? TokenType.BANG_EQUAL : TokenType.BANG);
            break;
        case '=':
            addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
            break;
        case '<':
            addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
            break;
        case '>':
            addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
            break;
        case '/':
            if (match('/')) {
                // A comment goes until the end of the line.
                while (peek() != '\n' && !isAtEnd()) advance();
            } else {
                addToken(TokenType.SLASH);
            }
            break;
        case ' ':
        case '\r':
        case '\t':
            // Ignore whitespace.
            break;

        case '\n':
            line++;
            break;
        case '"': string(); break;

        default:
            Veld.error(line, "Unexpected character.");
            break;
        }
    }

    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++;
            advance();
        }

        if (isAtEnd()) {
            Veld.error(line, "Unterminated string.");
            return;
        }

        // The closing ".
        advance();

        // Trim the surrounding quotes.
        String value = source.substring(start + 1, current - 1);
        addToken(TokenType.STRING, value);
    }

    /**
     * HELPER METHODS
     */
    private boolean isAtEnd() {
        return current >= source.length();
    }

    private char advance() {
        return source.charAt(current++);
    }

    private void addToken(TokenType type) {
        addToken(type, null);
    }

    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current++;
        return true;
    }
    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }
}