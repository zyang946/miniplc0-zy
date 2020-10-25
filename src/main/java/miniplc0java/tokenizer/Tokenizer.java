package miniplc0java.tokenizer;

import miniplc0java.error.TokenizeError;
import miniplc0java.util.Pos;
import miniplc0java.error.ErrorCode;

public class Tokenizer {

    private StringIter it;

    public Tokenizer(StringIter it) {
        this.it = it;
    }

    // 閺夆晜鐟╅崳鐑藉嫉椤掍焦闄嶉柡鍕靛灡閸忓倻鈧湱鍋熼獮锟� Iterator<Token> 闁汇劌瀚哥槐婵囨媴閸℃ɑ笑 Iterator 濞戞挸绉撮崢鎴犳媼閸涘﹤顫旂€殿喖鍊搁悥鍫曟晬鐏炶偐鑹鹃柡鍕靛灠濮樸劍娼诲▎鎰濞存粣鎷�
    /**
     * 闁兼儳鍢茶ぐ鍥ㄧ▔鐎ｂ晝顏卞☉鎿勬嫹 Token
     * 
     * @return
     * @throws TokenizeError 濠碘€冲€归悘澶屾喆閿濆棛鈧粙寮垫径濠勭＝閻㈩垳枪閸垶骞庡☉妯烘瘔
     */
    public Token nextToken() throws TokenizeError {
        it.readAll();

        // 閻犲搫鐤囩换鍐╃▕鐎ｎ亜顤呴柣銊ュ婢у秹寮垫径灞告晞闁谎嗘閻⊙呯箔閿燂拷
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
        // 閻犲洤鍢查敐鐐电矚閻氬绐�
        // 闁烩晜娼欓崺宀勫蓟閵壯勭畽濞戞挸顑勭粩瀛樼▔椤忓嫮鎽熺紒妤嬬細缁楀寮伴娑欐閻庢稒銇炵拹鐔奉潰閿燂拷:
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
        // -- 闁告挸绉风换妯荤▔閿熻姤绋夐鍕憻缂佹璁ｇ槐婵嬬嵁鐠鸿櫣鎽犻柛灞诲姀缁绘牗绋夐鍕憻缂佹鎷� 
        //
        // 閻熸瑱绲鹃悗鐣屸偓娑櫭崑宥夋儍閸曨偆鎽熺紒妤嬬細鐟曞棙绋夐悜妯伙骏缂佹绠戣ぐ鍧楀极鐎涙ɑ娈�
        // 閻熸瑱绲鹃悗浠嬪箣閹邦剙顫犻柛鎺撶懆缁绘垿宕堕悙瀛橈骏缂佹绠戣ぐ鍧楀极鐎涙ɑ娈剁紒顐ヮ嚙閻庣兘鎯冮崜鍣妅en闁挎稑鑻幆渚€宕氬▎鎺旂闁搞儳鍋熺槐顏嗘嫚閹达附鏅╅悹鍥锋嫹
        //
        // Token 闁活煉鎷� Value 閹煎瓨鏌ㄩ敐鐐哄礃濞嗘劖娈堕悗娑欘殘濞堟垿宕愰敓锟�+
    }

    private Token lexIdentOrKeyword() throws TokenizeError {
        // 閻犲洤鍢查敐鐐电矚閻氬绐�
        // 闁烩晜娼欓崺宀勫蓟閵壯勭畽濞戞挸顑勭粩瀛樼▔椤忓嫮鎽熺紒妤嬬細缁楀寮伴娑欐閻庢稒顨嗛崹銊р偓娑欘殕閻︽繃绋夐悜姗嗗壘:
        // -- 闁告挸绉风换妯荤▔閿熻姤绋夐鍕憻缂佹璁ｇ槐婵嬬嵁鐠鸿櫣鎽犻柛灞诲姀缁绘牗绋夐鍕憻缂佹鎷�
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
        // 閻忓繑绻嗛惁顖滀焊閸℃鎽犻柛灞诲妿濞堟垹鈧稒顨堥浣圭▔閼肩紟鎺楁煂婵犱浇绀嬮柛蹇斿▕閺侇厾鈧冻鎷�
        // -- 濠碘€冲€归悘澶愬及椤栨艾褰犻梺娆惧枛閻⊙囨晬鐏炶棄鐏熼弶鈺傛煥濞叉牠宕楅幎鑺ユ殯閻庢稒顨堢悮顐﹀垂鐎ｎ剚鐣� token
        // -- 闁告熬绠戦崹顖炴晬瀹€鍐闁搞儳鍋為悥锝囨嫚閸℃瑯鍎�
        //
        // Token 闁活煉鎷� Value 閹煎瓨鏌ㄩ敐鐐哄礃濞嗘劗鍨奸悹鍥ф椤戜線骞嬮弽褍褰犻梺娆惧枛閻⊙囨儍閸曨偆鎽熺紒妤嬬細鐟曪拷
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
                

            // 濠靛鍋勯崣鍡涘即閺夋埈妯嬮柣妯垮煐閿熸垝绀侀幏鐗堟交閺傛寧绀€閻犲浂鍘艰ぐ锟�

            default:
                // 濞戞挸绉烽鑽ゆ嫚閸℃氨绠瑰☉鎿冧海缁额參宕楅妷顖滅闁逛粙鏅茬花锟�
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
