package miniplc0java.tokenizer;

import miniplc0java.error.TokenizeError;
import miniplc0java.util.Pos;
import miniplc0java.error.ErrorCode;

public class Tokenizer {

    private StringIter it;

    public Tokenizer(StringIter it) {
        this.it = it;
    }

    // 杩欓噷鏈潵鏄兂瀹炵幇 Iterator<Token> 鐨勶紝浣嗘槸 Iterator 涓嶅厑璁告姏寮傚父锛屼簬鏄氨杩欐牱浜�
    /**
     * 鑾峰彇涓嬩竴涓� Token
     * 
     * @return
     * @throws TokenizeError 濡傛灉瑙ｆ瀽鏈夊紓甯稿垯鎶涘嚭
     */
    public Token nextToken() throws TokenizeError {
        it.readAll();

        // 璺宠繃涔嬪墠鐨勬墍鏈夌┖鐧藉瓧绗�
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
        // 璇峰～绌猴細
        // 鐩村埌鏌ョ湅涓嬩竴涓瓧绗︿笉鏄暟瀛椾负姝�:
        char next = it.nextChar();
        it.ptrNext = it.nextPos();
        String num = null;
        Pos startPos =it.currentPos();
        while(Character.isDigit(next)){
            num += next;
            next = it.nextChar();
            it.ptrNext = it.nextPos();
        }
        it.ptr = it.previousPos();
        it.ptrNext = it.nextPos();
        Pos endPos = it.currentPos();
        num = removeZero(num);
        int   value = Integer.parseInt(num);
        return new Token(TokenType.Uint,value,startPos,endPos);
        // -- 鍓嶈繘涓�涓瓧绗︼紝骞跺瓨鍌ㄨ繖涓瓧绗� 
        //
        // 瑙ｆ瀽瀛樺偍鐨勫瓧绗︿覆涓烘棤绗﹀彿鏁存暟
        // 瑙ｆ瀽鎴愬姛鍒欒繑鍥炴棤绗﹀彿鏁存暟绫诲瀷鐨則oken锛屽惁鍒欒繑鍥炵紪璇戦敊璇�
        //
        // Token 鐨� Value 搴斿～鍐欐暟瀛楃殑鍊�+
    }

    private Token lexIdentOrKeyword() throws TokenizeError {
        // 璇峰～绌猴細
        // 鐩村埌鏌ョ湅涓嬩竴涓瓧绗︿笉鏄暟瀛楁垨瀛楁瘝涓烘:
        // -- 鍓嶈繘涓�涓瓧绗︼紝骞跺瓨鍌ㄨ繖涓瓧绗�
        char next = it.nextChar();
        it.ptrNext = it.nextPos();
        String value = null;
        Pos startPos =it.currentPos();
        while(Character.isDigit(next)||Character.isAlphabetic(next)){
            value += next;
            next = it.nextChar();
            it.ptrNext = it.nextPos();
        }
        it.ptr = it.previousPos();
        it.ptrNext = it.nextPos();
        Pos endPos = it.currentPos();

        if(value.equals("Begin"))
            return new Token(TokenType.Begin,value,startPos,endPos);
        else if(value.equals("End"))
            return new Token(TokenType.End,value,startPos,endPos);
        else if(value.equals("Var"))
            return new Token(TokenType.Var,value,startPos,endPos);
        else if(value.equals("Const"))
            return new Token(TokenType.Const,value,startPos,endPos);
        else if(value.equals("Print"))
            return new Token(TokenType.Print,value,startPos,endPos);
        else            
            return new Token(TokenType.Ident,value,startPos,endPos);
        // 灏濊瘯灏嗗瓨鍌ㄧ殑瀛楃涓茶В閲婁负鍏抽敭瀛�
        // -- 濡傛灉鏄叧閿瓧锛屽垯杩斿洖鍏抽敭瀛楃被鍨嬬殑 token
        // -- 鍚﹀垯锛岃繑鍥炴爣璇嗙
        //
        // Token 鐨� Value 搴斿～鍐欐爣璇嗙鎴栧叧閿瓧鐨勫瓧绗︿覆
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
                

            // 濉叆鏇村鐘舵�佸拰杩斿洖璇彞

            default:
                // 涓嶈璇嗚繖涓緭鍏ワ紝鎽镐簡
                throw new TokenizeError(ErrorCode.InvalidInput, it.previousPos());
        }
    }

    private void skipSpaceCharacters() {
        while (!it.isEOF() && Character.isWhitespace(it.peekChar())) {
            it.nextChar();
        }
    }
    public static String removeZero(String str) {
        int len = str.length(), i = 0;
        while (i < len && str.charAt(i) == '0') {
            i++;
        }
        return str.substring(i);
    }
}
