package miniplc0java.tokenizer;

import miniplc0java.error.TokenizeError;
import miniplc0java.util.Pos;
import miniplc0java.error.ErrorCode;

public class Tokenizer {

    private StringIter it;

    public Tokenizer(StringIter it) {
        this.it = it;
    }

    // 闁哄鏅滈悷鈺呭闯閻戣棄瀚夋い鎺嶇劍闂勫秹鏌￠崟闈涚仭闁稿繐鍊婚埀顒€婀遍崑鐔肩嵁閿燂拷 Iterator<Token> 闂佹眹鍔岀€氬摜妲愬┑鍥ㄥ闁糕剝蓱绗� Iterator 婵炴垶鎸哥粔鎾储閹寸姵濯奸柛娑橈工椤梻鈧鍠栭崐鎼佹偉閸洘鏅悘鐐跺亹閼归箖鏌￠崟闈涚仩婵ǜ鍔嶅璇测枎閹邦喖顥庢繛瀛樼玻閹凤拷
    /**
     * 闂佸吋鍎抽崲鑼躲亹閸ャ劎鈻旈悗锝傛櫇椤忓崬鈽夐幙鍕 Token
     * 
     * @return
     * @throws TokenizeError 婵犵鈧啿鈧綊鎮樻径灞惧枂闁挎繂妫涢埀顑跨矙瀵灚寰勬繝鍕紳闁汇埄鍨虫灙闁割煈鍨堕獮搴♀槈濡儤鐦�
     */
    public Token nextToken() throws TokenizeError {
        // it.readAll();

        // // 闁荤姴鎼悿鍥╂崲閸愨晝鈻曢悗锝庝簻椤ゅ懘鏌ｉ妸銉ヮ仾濠⒀冪Ч瀵灚寰勭仦鍛婃櫈闂佽皫鍡橆棄闁烩姍鍛當闁跨噦鎷�
        // skipSpaceCharacters();

        // if (it.isEOF()) {
        //     return new Token(TokenType.EOF, "", it.currentPos(), it.currentPos());
        // }

        // char peek = it.peekChar();
        // if (Character.isDigit(peek)) {
        //     return lexUInt();
        // } else if (Character.isAlphabetic(peek)) {
        //     return lexIdentOrKeyword();
        // } else {
        //     return lexOperatorOrUnknown();
        // }
    }

    private Token lexUInt() throws TokenizeError {
        // // 闁荤姴娲ら崲鏌ユ晲閻愮數鐭氶柣姘嚟缁愶拷
        // // 闂佺儵鏅滃娆撳春瀹€鍕摕闁靛／鍕暯婵炴垶鎸搁鍕博鐎涙鈻旀い蹇撳閹界喓绱掑Δ瀣窗缂佹顦靛浼搭敍濞戞瑦顔嶉柣搴㈢⊕閵囩偟鎷归悢濂夋桨闁跨噦鎷�:
        // char next = it.nextChar();
        // it.ptrNext = it.nextPos();
        // String num = null;
        // Pos startPos =it.currentPos();
        // while(Character.isDigit(next)){
        //     num += next;
        //     next = it.nextChar();
        //     it.ptrNext = it.nextPos();
        // }
        // it.ptr = it.previousPos();
        // it.ptrNext = it.nextPos();
        // Pos endPos = it.currentPos();
        // num = removeZero(num);
        // int   value = Integer.parseInt(num);
        // return new Token(TokenType.Uint,value,startPos,endPos);
        // // -- 闂佸憡鎸哥粔椋庢崲濡崵鈻旈柨鐔诲Г缁嬪顢旈崟顓熸喕缂備焦顨愮拋锝囨濠靛宓侀悹楦挎閹界娀鏌涚仦璇插缂佺粯鐗楃粙澶愵敂閸曨厽鎲荤紓浣诡殣閹凤拷 
        // //
        // // 闁荤喐鐟辩徊楣冩倵閻ｅ备鍋撳☉娅亪宕戝澶嬪剭闁告洦鍋嗛幗鐔虹磼濡ゅ绱伴悷鏇炴缁嬪鎮滃Ο浼欓獜缂備焦顨愮粻鎴ｃ亹閸ф鏋侀悗娑櫳戝▓锟�
        // // 闁荤喐鐟辩徊楣冩倵娴犲绠ｉ柟閭﹀墮椤娀鏌涢幒鎾舵噯缂佺粯鍨垮畷鍫曟倷鐎涙﹫楠忕紓浣诡殣缁犳垼銇愰崸妤€鏋侀悗娑櫳戝▓鍓佺磼椤愩儺鍤欓柣搴ｅ厴閹啴宕滈崳濡卐n闂佹寧绋戦懟顖炲箚娓氣偓瀹曟艾鈻庨幒鏃傤唹闂佹悶鍎抽崑鐔烘椤忓棙瀚氶柟杈鹃檮閺呪晠鎮归崶閿嬪
        // //
        // // Token 闂佹椿鐓夐幏锟� Value 闁圭厧鐡ㄩ弻銊╂晲閻愬搫绀冩繛鍡樺姈濞堝爼鎮楀☉娆樻畼婵炲牊鍨垮畷鎰版晸閿燂拷+
    }

    private Token lexIdentOrKeyword() throws TokenizeError {
        // // 闁荤姴娲ら崲鏌ユ晲閻愮數鐭氶柣姘嚟缁愶拷
        // // 闂佺儵鏅滃娆撳春瀹€鍕摕闁靛／鍕暯婵炴垶鎸搁鍕博鐎涙鈻旀い蹇撳閹界喓绱掑Δ瀣窗缂佹顦靛浼搭敍濞戞瑦顔嶉柣搴㈢⊕椤ㄥ棝宕归妸褉鍋撳☉娆樻畷闁伙附绻冪粙澶愭倻濮楀棗澹�:
        // // -- 闂佸憡鎸哥粔椋庢崲濡崵鈻旈柨鐔诲Г缁嬪顢旈崟顓熸喕缂備焦顨愮拋锝囨濠靛宓侀悹楦挎閹界娀鏌涚仦璇插缂佺粯鐗楃粙澶愵敂閸曨厽鎲荤紓浣诡殣閹凤拷
        // char next = it.nextChar();
        // it.ptrNext = it.nextPos();
        // String value = null;
        // Pos startPos =it.currentPos();
        // while(Character.isDigit(next)||Character.isAlphabetic(next)){
        //     value += next;
        //     next = it.nextChar();
        //     it.ptrNext = it.nextPos();
        // }
        // it.ptr = it.previousPos();
        // it.ptrNext = it.nextPos();
        // Pos endPos = it.currentPos();

        // if(value.equals("Begin"))
        //     return new Token(TokenType.Begin,value,startPos,endPos);
        // else if(value.equals("End"))
        //     return new Token(TokenType.End,value,startPos,endPos);
        // else if(value.equals("Var"))
        //     return new Token(TokenType.Var,value,startPos,endPos);
        // else if(value.equals("Const"))
        //     return new Token(TokenType.Const,value,startPos,endPos);
        // else if(value.equals("Print"))
        //     return new Token(TokenType.Print,value,startPos,endPos);
        // else            
        //     return new Token(TokenType.Ident,value,startPos,endPos);
        // // 闁诲繐绻戠换鍡涙儊椤栨粈鐒婇柛鈩冾殘閹界娀鏌涚仦璇插婵炲牊鍨归埀顒佺⊕椤ㄥ牓顢栨担鍦枖闁艰偐绱熼幒妤佺厒濠电姳娴囩粈瀣煕韫囨柨鈻曢柡渚囧幘閳ь剚鍐婚幏锟�
        // // -- 婵犵鈧啿鈧綊鎮樻径鎰強妞ゆ牗鑹捐ぐ鐘绘⒑濞嗘儳鏋涢柣鈯欏洦鏅悘鐐舵閻忕喖寮堕埡鍌涚叆婵炲弶鐗犲畷妤呭箮閼恒儲娈柣搴㈢⊕椤ㄥ牏鎮锕€鍨傞悗锝庡墯閻ｏ拷 token
        // // -- 闂佸憡鐔粻鎴﹀垂椤栫偞鏅€光偓閸愵亞顔夐梺鎼炲劤閸嬬偤鎮ラ敐鍥ㄥ珰闁糕剝鐟崕锟�
        // //
        // // Token 闂佹椿鐓夐幏锟� Value 闁圭厧鐡ㄩ弻銊╂晲閻愬搫绀冩繛鍡樺姉閸ㄥジ鎮归崶褎顥犳い鎴滅窔楠炲寮借瑜扮娀姊哄▎鎯ф灈闁烩姍鍥ㄥ剭闁告洦鍋嗛幗鐔虹磼濡ゅ绱伴悷鏇嫹
    }

    private Token lexOperatorOrUnknown() throws TokenizeError {
        // switch (it.nextChar()) {
        //     case '+':
        //         return new Token(TokenType.Plus, '+', it.previousPos(), it.currentPos());
        //     case '-':
        //         return new Token(TokenType.Minus,'-',it.previousPos(),it.currentPos());
        //     case '*':
        //         return new Token(TokenType.Mult,'*',it.previousPos(),it.currentPos());

        //     case '/':
        //         return new Token(TokenType.Div,'/',it.previousPos(),it.currentPos());

        //     case ';':
        //         return new Token(TokenType.Semicolon,';',it.previousPos(),it.currentPos());
            
        //     case '=':
        //         return new Token(TokenType.Equal,'=',it.previousPos(),it.currentPos());

        //     case '(':
        //         return new Token(TokenType.LParen,'(',it.previousPos(),it.currentPos());         

        //     case ')':
        //         return new Token(TokenType.RParen,')',it.previousPos(),it.currentPos());
                

        //     // 婵犻潧顦介崑鍕矗閸℃稑鍗抽柡澶嬪焾濡鏌ｅΟ鍨厫闁跨喐鍨濈粈渚€骞忛悧鍫熶氦闁哄倹瀵х粈鈧柣鐘叉祩閸樿壈銇愰敓锟�

        //     default:
        //         // 婵炴垶鎸哥粔鐑筋敇閼姐倖瀚氶柛鈩冩皑缁犵懓鈽夐幙鍐ф捣缂侀鍙冨畷妤呭Ψ椤栨粎顦梺閫涚矙閺呰尙鑺遍敓锟�
        //         throw new TokenizeError(ErrorCode.InvalidInput, it.previousPos());
        // }
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
