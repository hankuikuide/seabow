package com.crhms.seabow.utils;

import java.io.*;

public class FileEncodingConverter {
    /**
     * 将本地文件以哪种编码输出
     * @param inputfile 输入文件的路径
     * @param outfile 输出文件的路径
     * @param code 输出文件的编码
     * @throws IOException
     */
    public  void convert(String inputfile,String outfile,String code) throws IOException {
        StringBuffer sb = new StringBuffer();
        //得到当前文件的编码
        String ch=getCharset(inputfile);
        InputStreamReader isr=null;
        OutputStreamWriter osw =null;
        //根据当前文件编码进行解码
        if(ch.equals("UTF8")){
            isr= new InputStreamReader(new FileInputStream(inputfile), "UTF-8");
        }else if(ch.equals("Unicode")){
            isr= new InputStreamReader(new FileInputStream(inputfile), "Unicode");
        }else {
            isr= new InputStreamReader(new FileInputStream(inputfile), "GB2312");
        }
        //将字符串存入StringBuffer中
        BufferedReader br = new BufferedReader(isr);

        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        isr.close();

        //以哪种方式写入文件
        if("UTF-8".equals(code)){
            osw = new OutputStreamWriter(new FileOutputStream(outfile), "UTF-8");
        }else if("GB2312".equals(code)){
            osw = new OutputStreamWriter(new FileOutputStream(outfile), "GB2312");
        }else if("Unicode".equals(code)){
            osw = new OutputStreamWriter(new FileOutputStream(outfile), "Unicode");
        }else{
            osw = new OutputStreamWriter(new FileOutputStream(outfile), "UTF-8");
        }
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(sb.toString());
        bw.close();
        osw.close();
    }

    /**
     * 根据文件路径判断编码
     * @param str
     * @return
     * @throws IOException
     */
    private String getCharset(String str) throws IOException{
        EncodingDetect s = new EncodingDetect();
        String code = EncodingDetect.javaname[s.detectEncoding(new File(str))];
        return code;
    }
}
