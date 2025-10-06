## Veld Programming Language

Veld is a custom programming language implemented using traditional compiler design principles. The interpreter follows a clean three-stage pipeline: lexical analysis (scanning), syntax analysis (parsing), and evaluation (interpretation). Currently focused on expression evaluation, with plans to expand into a full-featured object-oriented language.

## Features

### Current Implementation
- **Complete Expression Evaluation**: Supports arithmetic, comparison, logical, and string operations
- **Dynamic Typing**: Automatic type inference and conversion
- **Operator Precedence**: Proper mathematical precedence (parentheses → unary → multiplication/division → addition/subtraction → comparison → equality)
- **String Operations**: String concatenation with automatic type conversion
- **Boolean Logic**: Truthiness evaluation and logical operators
- **Dual Execution Modes**: 
  - Interactive REPL for live coding
  - File execution for script running
- **Robust Error Handling**: Line-number precise error reporting for both syntax and runtime errors
- **Comment Support**: Both single-line (`//`) and multi-line (`/* */`) comments

## Veld Syntax

Veld supports intuitive expression syntax with proper operator precedence. Here's everything you need to start writing Veld code:

### Data Types
- **Numbers**: Double-precision floating point (e.g., `42`, `3.14`, `-17.5`)
- **Strings**: Text enclosed in double quotes (e.g., `"Hello"`, `"Veld Language"`)
- **Booleans**: `true` and `false`
- **Null**: `null` represents no value

### Operators
- **Arithmetic**: `+`, `-`, `*`, `/`
- **Comparison**: `<`, `<=`, `>`, `>=`, `==`, `!=`
- **Logical**: `!` (negation)
- **Grouping**: `()` for precedence control

### Basic Examples

```javascript
// Arithmetic operations
42 + 18 * 2         // 78
(15 - 3) / 4        // 3
100 - 25 * 2        // 50

// String operations
"Veld" + " " + "Language"    // "Veld Language"
"Number: " + 42              // "Number: 42"
"Result: " + (10 + 5)        // "Result: 15"

// Comparisons
25 > 20             // true
"apple" == "orange" // false
3.14 <= 3.15        // true

// Logical operations
!false              // true
!(5 < 3)           // true
!null               // true

// Mixed operations
"Score: " + (95 >= 90)       // "Score: true"
"Grade: " + (85 > 90)        // "Grade: false"
```

### Expression Rules
- **Operator Precedence**: Parentheses → Unary (`!`, `-`) → Multiplication/Division → Addition/Subtraction → Comparison → Equality
- **String Concatenation**: Use `+` to join strings, or mix strings with other types
- **Type Flexibility**: Numbers and strings can be mixed with `+` for concatenation
- **Truthiness**: `null` and `false` are falsy, everything else is truthy

## Architecture

The interpreter follows a clean separation of concerns:

```
Source Code → Scanner → Tokens → Parser → AST → Interpreter → Output
```

### Core Components

1. **Scanner** (`Scanner.java`): Lexical analysis - converts source code into tokens
2. **Parser** (`Parser.java`): Syntax analysis - builds Abstract Syntax Tree using recursive descent
3. **Interpreter** (`Interpreter.java`): Evaluation - executes the AST using the Visitor pattern
4. **AST** (`Expr.java`): Expression representations with Visitor pattern support
5. **Error Handling**: Comprehensive error reporting with source location tracking

### Design Patterns
- **Visitor Pattern**: Clean separation between AST node types and operations
- **Recursive Descent**: Intuitive parser structure matching grammar rules
- **Code Generation**: Metaprogramming tool for AST class generation

## Usage

### Interactive Mode (REPL)
```bash
java -cp out/production/veld com.veld.Veld
> 2 + 3 * 4
14
> "Hello, " + "World!"
Hello, World!
> (5 + 3) >= 8
true
```

### Prerequisites
- Java 8 or higher
- Standard Java development environment

### Running
```bash
# Interactive mode
java -cp out/production/veld com.veld.Veld

# File execution
java -cp out/production/veld com.veld.Veld your_script.veld

# AST visualization (for development)
java -cp out/production/veld com.veld.AstPrinter
```

## Error Handling
- **Lexical Errors**: Invalid characters, unterminated strings
- **Syntax Errors**: Malformed expressions, missing operators
- **Runtime Errors**: Type mismatches, division by zero
- All errors include precise line number information

## Contributing

This is a personal learning project demonstrating compiler implementation principles. The codebase follows clean architecture patterns and is designed for extensibility.

## License

This project is for educational and portfolio purposes.

---

*Built with Java, following traditional compiler design principles and clean code practices.*
