package cn.zlin.demo.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class FtpFileUtil {
    //ftp服务器ip地址
    private static final String FTP_ADDRESS = "120.77.159.73";
    //端口号
    private static final int FTP_PORT = 21;
    //用户名
    private static final String FTP_USERNAME = "zlin";
    //密码
    private static final String FTP_PASSWORD = "smileatlife";

    public  static boolean uploadFile(String filePath, String originFileName, InputStream input) throws IOException{
        //FTP完整路径
        String path = Constant.ftpPath + filePath;
        boolean success = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return success;
            }
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftp.makeDirectory(path);
            ftp.changeWorkingDirectory(path);
            ftp.storeFile(originFileName,input);
            input.close();
            ftp.logout();
            success = true;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return success;
    }

    /**
     * 删除文件
     * @param workingDir 文件所在路径
     * @param fileName 文件名称
     * @return 删除结果
     * @throws IOException
     */
    public static Boolean deleteFile(String workingDir, String fileName) throws IOException {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            ftp.changeWorkingDirectory(workingDir);
            int flag = ftp.dele(fileName);
            if (flag == 250) {
                result = true;
            }
            ftp.logout();
        }finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 复制文件
     * @param sourceFileName 旧名
     * @param targeFileName 新名
     * @param sourceDir 旧路径
     * @param targetDir 新路径
     * @throws IOException
     */
    public static void copyFile(String sourceFileName, String targeFileName, String sourceDir, String targetDir) throws IOException {
        FTPClient ftp = new FTPClient();
        ftp.setControlEncoding("GBK");
        InputStream is = null;
        try {
            int reply;
            ftp.connect(FTP_ADDRESS, FTP_PORT);// 连接FTP服务器
            ftp.login(FTP_USERNAME, FTP_PASSWORD);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
            }
            ftp.makeDirectory(targetDir);
            // 变更工作路径
            ftp.changeWorkingDirectory(sourceDir);
            // 设置以二进制流的方式传输
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            is = ftp.retrieveFileStream(new String(sourceFileName.getBytes("GBK"), "iso-8859-1"));
            // 主动调用一次getReply()把接下来的226消费掉. 这样做是可以解决这个返回null问题
            ftp.getReply();
            if (is != null) {
                ftp.changeWorkingDirectory(targetDir);
                ftp.storeFile(new String(targeFileName.getBytes("GBK"), "iso-8859-1"), is);
            }
            ftp.logout();
        }finally {
            if (is != null) {
                is.close();
            }
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }
}
