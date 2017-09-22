package test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test1 {

//    public void WriteStringToFile(String filePath) {
//        try {
//            File file = new File(filePath);
//            PrintStream ps = new PrintStream(new FileOutputStream(file));
//            ps.println("http://www.jb51.net");// 往文件里写入字符串
//            ps.append("http://www.jb51.net");// 在已有的基础上添加字符串
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public void WriteStringToFile2(String filePath) {
//        try {
//            FileWriter fw = new FileWriter(filePath, true);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.append("在已有的基础上添加字符串");
//            bw.write("abc\r\n ");// 往已有的文件上添加字符串
//            bw.write("def\r\n ");
//            bw.write("hijk ");
//            bw.close();
//            fw.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void WriteStringToFile3(String filePath) {
//        try {
//            PrintWriter pw = new PrintWriter(new FileWriter(filePath));
//            pw.println("abc ");
//            pw.println("def ");
//            pw.println("hef ");
//            pw.close();
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    public void WriteStringToFile4(String filePath) {
//        try {
//            RandomAccessFile rf = new RandomAccessFile(filePath, "rw");
//            rf.writeBytes("op\r\n");
//            rf.writeBytes("app\r\n");
//            rf.writeBytes("hijklllll");
//            rf.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void WriteStringToFile5(String filePath) {
//        try {
//            FileOutputStream fos = new FileOutputStream(filePath);
//            String s = "http://www.jb51.netl";
//            fos.write(s.getBytes());
//            fos.close();
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {

        String filePath = "D:\\idea\\content.txt";

        String outPath = "D:\\idea\\template.txt";

//        String str = "INSERT INTO grjy_1018772982317465_2017_06(WID, KH, SFRZH, KXLH, JYRQ, " +
//                "JYSJ, JYDD, JYLX, JYLSH, SHDM, ZDJH, JYJE, JYYE, CLRQ, TBRQ, TBLX, CZRQ, " +
//                "CZZ, CZZXM, BZ1, XFLX, SCHOOL_ID) ";
//        String str2 = "KXLH, JYRQ, JYSJ, JYDD, JYLX, JYLSH, SHDM, ZDJH, JYJE, JYYE, CLRQ, TBRQ, TBLX, CZRQ," +
//                " CZZ, CZZXM, BZ1, XFLX, SCHOOL_ID "
//                + "FROM grjy_1018772982317465_2017_06 " +
//                "WHERE KH = 20170102 and SCHOOL_ID=1018772982317465";

//        String str = "INSERT INTO t_comapp_score_cjcx_1018772982317465(wid, xh, xm, zydm, kch, " +
//                "kcm, ywkcm, kclb, kcxz, xf, kssj, xdfs, cj, kcjd, xnxqdm, sfxz, sfck, " +
//                "dqnj, school_id) ";
//        String str2 = "zydm, kch, kcm, ywkcm, kclb, kcxz, xf, kssj, xdfs, cj, kcjd, xnxqdm, sfxz, sfck, dqnj, school_id "
//                + "FROM t_comapp_score_cjcx_1018772982317465 " +
//                "WHERE xh = 20170102";

        // "WHERE xh = 20170102 and school_id=1018772982317465";

        String str = "INSERT INTO t_comapp_score_zdxf(wid, xh, zydm, dqnj, wzxf, school_id) ";
        String str2 = "zydm, dqnj, wzxf, school_id "
                + "FROM t_comapp_score_zdxf " +
                "WHERE xh = 20170102";

       // String temlate = "SELECT '1','01110059', '居安静',";

        String template = "D:\\idea\\temp\\t_comapp_score_zdxf.txt";

        try {
            File filename = new File(filePath);
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line = "";
            line = br.readLine();
            int count = 300123;
            List<String> list = new ArrayList<String>();
            while (line != null) {
                File filename2 = new File(template);
                InputStreamReader reader2 = new InputStreamReader(
                        new FileInputStream(filename2));
                BufferedReader br2 = new BufferedReader(reader2);
                String line2 = "";
                line2 = br2.readLine();

                while(line2 != null){
                    count++;
                    String res = "SELECT " + "'" + count + "'" + ",";
                    String[] arr = line.split(",");
                    String[] arr2 = line2.split(",");
                    //res = res  + "'" + arr[0] + "'" +  "," + "'" + arr[1] + "'" +  ",";
                    res = res  + "'" + arr[0] + "'" +  ",";
                    list.add(str + res + str2 + " and WID = " + arr2[0].trim() + ";");
                    line2 = br2.readLine();
                }
                line = br.readLine();
            }
            File writename = new File(outPath);
            writename.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));

            for(String s : list){
                out.write(s  +  "\r\n");
            }

            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
