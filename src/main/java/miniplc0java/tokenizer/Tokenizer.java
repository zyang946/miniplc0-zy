package miniplc0java.tokenizer;

import miniplc0java.error.TokenizeError;
import miniplc0java.util.Pos;
import miniplc0java.error.ErrorCode;

public class Tokenizer {

    private StringIter it;

    public Tokenizer(StringIter it) {
        this.it = it;
    }

    // 鏉╂瑩鍣烽張顒佹降閺勵垱鍏傜€圭偟骞� Iterator<Token> 閻ㄥ嫸绱濇担鍡樻Ц Iterator 娑撳秴鍘戠拋鍛婂瀵倸鐖堕敍灞肩艾閺勵垰姘ㄦ潻娆愮壉娴滐拷
    /**
     * 閼惧嘲褰囨稉瀣╃娑擄拷 Token
     * 
     * @return
     * @throws TokenizeError 婵″倹鐏夌憴锝嗙€介張澶婄磽鐢ǹ鍨幎娑樺毉
     */
    public Token nextToken() throws TokenizeError {
        it.readAll();

        // 鐠哄疇绻冩稊瀣閻ㄥ嫭澧嶉張澶屸敄閻ц棄鐡х粭锟�
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
        // 鐠囧嘲锝炵粚鐚寸窗
        // 閻╂潙鍩岄弻銉ф箙娑撳绔存稉顏勭摟缁楋缚绗夐弰顖涙殶鐎涙ぞ璐熷锟�:
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
        // -- 閸撳秷绻樻稉锟芥稉顏勭摟缁楋讣绱濋獮璺虹摠閸屻劏绻栨稉顏勭摟缁楋拷 
        //
        // 鐟欙絾鐎界€涙ê鍋嶉惃鍕摟缁楋缚瑕嗘稉鐑樻￥缁楋箑褰块弫瀛樻殶
        // 鐟欙絾鐎介幋鎰閸掓瑨绻戦崶鐐存￥缁楋箑褰块弫瀛樻殶缁鐎烽惃鍓噊ken閿涘苯鎯侀崚娆掔箲閸ョ偟绱拠鎴︽晩鐠囷拷
        //
        // Token 閻拷 Value 鎼存柨锝為崘娆愭殶鐎涙娈戦崐锟�+
    }

    private Token lexIdentOrKeyword() throws TokenizeError {
        // 鐠囧嘲锝炵粚鐚寸窗
        // 閻╂潙鍩岄弻銉ф箙娑撳绔存稉顏勭摟缁楋缚绗夐弰顖涙殶鐎涙鍨ㄧ€涙鐦濇稉鐑橆剾:
        // -- 閸撳秷绻樻稉锟芥稉顏勭摟缁楋讣绱濋獮璺虹摠閸屻劏绻栨稉顏勭摟缁楋拷
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
        // 鐏忔繆鐦亸鍡楃摠閸屻劎娈戠€涙顑佹稉鑼缎掗柌濠佽礋閸忔娊鏁€涳拷
        // -- 婵″倹鐏夐弰顖氬彠闁款喖鐡ч敍灞藉灟鏉╂柨娲栭崗鎶芥暛鐎涙琚崹瀣畱 token
        // -- 閸氾箑鍨敍宀冪箲閸ョ偞鐖ｇ拠鍡欘儊
        //
        // Token 閻拷 Value 鎼存柨锝為崘娆愮垼鐠囧棛顑侀幋鏍у彠闁款喖鐡ч惃鍕摟缁楋缚瑕�
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
                

            // 婵夘偄鍙嗛弴鏉戭樋閻樿埖锟戒礁鎷版潻鏂挎礀鐠囶厼褰�

            default:
                // 娑撳秷顓荤拠鍡氱箹娑擃亣绶崗銉礉閹介晲绨�
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
