package qqai.suanfa.sort;

/**
 * @author qqai
 * @createTime 2020/11/13 21:28
 * @description：递增序列
 */

public class DiZengXunLie {

    public static void main(String[] args) {
        String s = "VLPWJVVNNZSWFGHSFRBCOIJTPYNEURPIGKQGPSXUGNELGRVZAG\n" +
                "SDLLOVGRTWEYZKKXNKIRWGZWXWRHKXFASATDWZAPZRNHTNNGQF\n" +
                "ZGUGXVQDQAEAHOQEADMWWXFBXECKAVIGPTKTTQFWSWPKRPSMGA\n" +
                "BDGMGYHAOPPRRHKYZCMFZEDELCALTBSWNTAODXYVHQNDASUFRL\n" +
                "YVYWQZUTEPFSFXLTZBMBQETXGXFUEBHGMJKBPNIHMYOELYZIKH\n" +
                "ZYZHSLTCGNANNXTUJGBYKUOJMGOGRDPKEUGVHNZJZHDUNRERBU\n" +
                "XFPTZKTPVQPJEMBHNTUBSMIYEGXNWQSBZMHMDRZZMJPZQTCWLR\n" +
                "ZNXOKBITTPSHEXWHZXFLWEMPZTBVNKNYSHCIQRIKQHFRAYWOPG\n" +
                "MHJKFYYBQSDPOVJICWWGGCOZSBGLSOXOFDAADZYEOBKDDTMQPA\n" +
                "VIDPIGELBYMEVQLASLQRUKMXSEWGHRSFVXOMHSJWWXHIBCGVIF\n" +
                "GWRFRFLHAMYWYZOIQODBIHHRIIMWJWJGYPFAHZZWJKRGOISUJC\n" +
                "EKQKKPNEYCBWOQHTYFHHQZRLFNDOVXTWASSQWXKBIVTKTUIASK\n" +
                "PEKNJFIVBKOZUEPPHIWLUBFUDWPIDRJKAZVJKPBRHCRMGNMFWW\n" +
                "CGZAXHXPDELTACGUWBXWNNZNDQYYCIQRJCULIEBQBLLMJEUSZP\n" +
                "RWHHQMBIJWTQPUFNAESPZHAQARNIDUCRYQAZMNVRVZUJOZUDGS\n" +
                "PFGAYBDEECHUXFUZIKAXYDFWJNSAOPJYWUIEJSCORRBVQHCHMR\n" +
                "JNVIPVEMQSHCCAXMWEFSYIGFPIXNIDXOTXTNBCHSHUZGKXFECL\n" +
                "YZBAIIOTWLREPZISBGJLQDALKZUKEQMKLDIPXJEPENEIPWFDLP\n" +
                "HBQKWJFLSEXVILKYPNSWUZLDCRTAYUUPEITQJEITZRQMMAQNLN\n" +
                "DQDJGOWMBFKAIGWEAJOISPFPLULIWVVALLIIHBGEZLGRHRCKGF\n" +
                "LXYPCVPNUKSWCCGXEYTEBAWRLWDWNHHNNNWQNIIBUCGUJYMRYW\n" +
                "CZDKISKUSBPFHVGSAVJBDMNPSDKFRXVVPLVAQUGVUJEXSZFGFQ\n" +
                "IYIJGISUANRAXTGQLAVFMQTICKQAHLEBGHAVOVVPEXIMLFWIYI\n" +
                "ZIIFSOPCMAWCBPKWZBUQPQLGSNIBFADUUJJHPAIUVVNWNWKDZB\n" +
                "HGTEEIISFGIUEUOWXVTPJDVACYQYFQUCXOXOSSMXLZDQESHXKP\n" +
                "FEBZHJAGIFGXSMRDKGONGELOALLSYDVILRWAPXXBPOOSWZNEAS\n" +
                "VJGMAOFLGYIFLJTEKDNIWHJAABCASFMAKIENSYIZZSLRSUIPCJ\n" +
                "BMQGMPDRCPGWKTPLOTAINXZAAJWCPUJHPOUYWNWHZAKCDMZDSR\n" +
                "RRARTVHZYYCEDXJQNQAINQVDJCZCZLCQWQQIKUYMYMOVMNCBVY\n" +
                "ABTCRRUXVGYLZILFLOFYVWFFBZNFWDZOADRDCLIRFKBFBHMAXX\n";
        String[] strings = s.split("\\n");
        char[][] chars = new char[30][50];
        for (int i = 0; i < strings.length; i++) {
            chars[i] = strings[i].toCharArray();
        }
        String res = get(chars);
        System.out.println(res);
    }

    private static String get(char[][] c) {
        int ans = 0;
        int n = 30;
        int m = 50;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 1; k < m; k++) {
                    if (j + k < c[i].length && c[i][j] < c[i][j + k]) {
                        str.append(c[i][j]).append(",").append(c[i][j + k]);
                        ans++;
                    }
                    if (i + k < c.length && c[i][j] < c[i + k][j]) {
                        str.append(c[i][j]).append(",").append(c[i + k][j]);
                        ans++;
                    }
                    if (j + k < c[i].length && i + k < c.length && c[i][j] < c[i + k][j + k]) {
                        str.append(c[i][j]).append(",").append(c[i + k][j + k]);
                        ans++;
                    }
                    if (j + k < c[i].length && i - k >= 0 && c[i][j] != c[i - k][j + k]) {
                        str.append(c[i][j]).append(",").append(c[i - k][j + k]);
                        ans++;
                    }
                }
            }
        }
        System.out.println(ans);
        return str.toString();
    }
}
