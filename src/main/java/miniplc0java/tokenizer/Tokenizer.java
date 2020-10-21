package miniplc0java.tokenizer;

import miniplc0java.error.TokenizeError;
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
        char next = it.NextChar();
        it.ptrNext = it.nextPos();
        String num = null;
        Pos startPos =it.currentPos();
        while(Character.isDigit(next)){
            num += next;
            next = it.NextChar();
            it.ptrNext = it.nextPos();
        }
        it.ptr = it.previousPos();
        it.ptrNext = it.nextPos();
        Pos endPos = it.currentPos();
        num = removeZero(num);
        try {
            int Value = Integer.parseInt(num);
        } catch (NumberFormatException e) {     
            e.printStackTrace();
        }
        return new Token(TokenType.Uint,value,startPos,endPos);
        // -- 前进一个字符，并存储这个字符 
        //
        // 解析存储的字符串为无符号整数
        // 解析成功则返回无符号整数类型的token，否则返回编译错误
        //
        // Token 的 Value 应填写数字的值
        throw new Error("Not implemented");
    }

    private Token lexIdentOrKeyword() throws TokenizeError {
        // 请填空：
        // 直到查看下一个字符不是数字或字母为止:
        // -- 前进一个字符，并存储这个字符
        char next = it.NextChar();
        it.ptrNext = it.nextPos();
        String value = null;
        Pos startPos =it.currentPos();
        while(Character.isDigit(next)||Character.isAlphabetic(next)){
            value += next;
            next = it.NextChar();
            it.ptrNext = it.nextPos();
        }
        it.ptr = it.previousPos();
        it.ptrNext = it.nextPos();
        Pos endPos = it.currentPos();

        if(value.equals("Begin"))
            TokenType type = TokenType.Begin;
        else if(value.equals("End"))
            TokenType type = TokenType.End;
        else if(value.equals("Var"))
            TokenType type = TokenType.Var;
        else if(value.equals("Const"))
            TokenType type = TokenType.Const;
        else if(value.equals("Print"))
            TokenType type = TokenType.Print;
        else TokenType type = TokenType.Ident;
        return new Token(type,value,startPos,endPos);
        // 尝试将存储的字符串解释为关键字
        // -- 如果是关键字，则返回关键字类型的 token
        // -- 否则，返回标识符
        //
        // Token 的 Value 应填写标识符或关键字的字符串
        throw new Error("Not implemented");
    }

    private Token lexOperatorOrUnknown() throws TokenizeError {
        switch (it.nextChar()) {
            case '+':
                return new Token(TokenType.Plus, '+', it.previousPos(), it.currentPos());
                throw new Error("not implemented");
            case '-':
                return new Token（TokenType.Minus,'-',it.previousPos(),it.currentPos());
                throw new Error("Not implemented");

            case '*':
                return new Token（TokenType.Mult,'*',it.previousPos(),it.currentPos());
                throw new Error("Not implemented");

            case '/':
                return new Token（TokenType.Div,'/',it.previousPos(),it.currentPos());
                throw new Error("Not implemented");

            case ';':
                return new Token（TokenType.Semicolon,';',it.previousPos(),it.currentPos());
                throw new Error("Not implemented");
            
            case '=':
                return new Token（TokenType.Equal,'=',it.previousPos(),it.currentPos());
                throw new Error("Not implemented");

            case '(':
                return new Token（TokenType.Lparen,'(',it.previousPos(),it.currentPos());
                throw new Error("Not implemented");            

            case ')':
                return new Token（TokenType.Rparen,')',it.previousPos(),it.currentPos());
                throw new Error("Not implemented");
                    
            case ''


            // 填入更多状态和返回语句

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
