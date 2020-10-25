package miniplc0java.tokenizer;

import miniplc0java.error.TokenizeError;
import miniplc0java.util.Pos;
import miniplc0java.error.ErrorCode;

public class Tokenizer {

    private StringIter it;

    public Tokenizer(StringIter it) {
        this.it = it;
    }

    // 这里本来是想实现 Iterator<Token> 的，但是 Iterator 不允许抛异常，于是就这样了
    /**
     * 获取下一个 Token
     * 
     * @return
     * @throws TokenizeError 如果解析有异常则抛出
     */
    public Token nextToken() throws TokenizeError {
        it.readAll();

        // 跳过之前的所有空白字符
        skipSpaceCharacters();

        if (it.isEOF()) {
            return new Token(TokenType.EOF, "", it.currentPos(), it.currentPos());
        }

        char peek = it.peekChar();
        if (Character.isDigit(peek)) {
            return lexUInt();
        } else if (Character.isAlphabetic(peek)) {
            return lexIdentOrKeyword();
        } else {
            return lexOperatorOrUnknown();
        }
    }

    private Token lexUInt() throws TokenizeError {
        // 请填空：
        // 直到查看下一个字符不是数字为止:
        // -- 前进一个字符，并存储这个字符
        //
        // 解析存储的字符串为无符号整数
        // 解析成功则返回无符号整数类型的token，否则返回编译错误
        //
        // Token 的 Value 应填写数字的值
        Pos startPos =it.currentPos();
        char peek = it.peekChar();
        String num = "";
        while(Character.isDigit(peek)){
            num += it.nextChar();
            peek = it.peekChar();
        }
        Pos endPos = it.currentPos();
        int value = Integer.parseInt(num);
        return new Token(TokenType.Uint,value,startPos,endPos);
    }

    private Token lexIdentOrKeyword() throws TokenizeError {
        Pos startPos =it.currentPos();
        char peek = it.peekChar();
        String value = "";
        while(Character.isDigit(peek)||Character.isAlphabetic(peek)){
            value += it.nextChar();
            peek = it.peekChar();
        }
        Pos endPos = it.currentPos();
        if(value.equals("begin"))
            return new Token(TokenType.Begin,value,startPos,endPos);
        else if(value.equals("end"))
            return new Token(TokenType.End,value,startPos,endPos);
        else if(value.equals("var"))
            return new Token(TokenType.Var,value,startPos,endPos);
        else if(value.equals("const"))
            return new Token(TokenType.Const,value,startPos,endPos);
        else if(value.equals("print"))
            return new Token(TokenType.Print,value,startPos,endPos);
        else            
            return new Token(TokenType.Ident,value,startPos,endPos);
    }

    private Token lexOperatorOrUnknown() throws TokenizeError {
        switch (it.nextChar()) {
            case '+':
                return new Token(TokenType.Plus, '+', it.previousPos(), it.currentPos());
            case '-':
                return new Token(TokenType.Minus,'-',it.previousPos(),it.currentPos());
            case '*':
                return new Token(TokenType.Mult,'*',it.previousPos(),it.currentPos());

            case '/':
                return new Token(TokenType.Div,'/',it.previousPos(),it.currentPos());

            case ';':
                return new Token(TokenType.Semicolon,';',it.previousPos(),it.currentPos());
            
            case '=':
                return new Token(TokenType.Equal,'=',it.previousPos(),it.currentPos());

            case '(':
                return new Token(TokenType.LParen,'(',it.previousPos(),it.currentPos());         

            case ')':
                return new Token(TokenType.RParen,')',it.previousPos(),it.currentPos());

            default:
                // 不认识这个输入，摸了
                throw new TokenizeError(ErrorCode.InvalidInput, it.previousPos());
        }
    }

    private void skipSpaceCharacters() {
        while (!it.isEOF() && Character.isWhitespace(it.peekChar())) {
            it.nextChar();
        }
    }
}
