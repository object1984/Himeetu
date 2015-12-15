package com.himeetu.util;


import android.content.Context;
import android.os.Environment;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;

import com.himeetu.app.Constants;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class FileUtil {
    private static final String TAG = "FileUtil";

    /**
     * 删除文件夹
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    FileUtil.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        } else {
            LogUtil.d(TAG, "所删除的文件不存在！" + '\n');
        }
    }

    /**
     * 删除文件夹
     */
    public static void deleteFile(String dir) {
        FileUtil.deleteFile(new File(dir));
    }

    /**
     * 将内存的数据保存到指定文件
     *
     * @param pathName   全路径名称 如： D:\\abc\123.txt
     * @param memoryData 要保存到文件的内存数据
     */
    public static void saveFileFromMemory(String pathName, String memoryData) {
        BufferedWriter fw = null;
        FileWriter fileOut = null;
        try {
            File file = new File(pathName);
            fileOut = new FileWriter(file);
            fw = new BufferedWriter(fileOut);
            fw.write(new String(memoryData.getBytes("ISO-8859-1")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null)
                try {
                    fw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (fileOut != null)
                try {
                    fileOut.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

    /**
     * 将内存的数据保存到指定文件
     *
     * @param pathName   全路径名称 如： D:\\abc\123.txt
     * @param memoryData 要保存到文件的内存数据
     */
    public static void saveBytesFileFromMemory(String pathName,
                                               String memoryData) {
        FileUtil.deleteFile(pathName);
        BufferedWriter fw = null;
        FileOutputStream out = null;
        try {
            File file = new File(pathName);
            out = new FileOutputStream(file, true);
            out.write(memoryData.getBytes("utf-8"));
            // fw.write(new String(memoryData.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null)
                try {
                    fw.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
    }

    public static String filterForbidChator(String pathName) {
        return pathName.replaceAll("[/:*<>|]+", "");
    }

    /**
     * save file
     *
     * @param is         : InputStream
     * @param targetFile : target file path
     * @author gt
     */
    public static File saveFile(InputStream is, String targetFile, int maxLength)
            throws Exception {

        try {
            File file = new File(targetFile);
            if (file.exists() == false)
                file.createNewFile();
            OutputStream os = new FileOutputStream(file);
            int byteRead = 0;
            byte[] buffer = new byte[1024];

            int count = 0;
            while ((byteRead = is.read(buffer, 0, 1024)) != -1) {
                count += byteRead;
                if (count > maxLength) {
                    os.close();
                    is.close();
                    file.delete();
                    throw new Exception("File length over maxLength");
                }

                os.write(buffer, 0, byteRead);
            }
            os.close();
            is.close();
            return file;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * get upload path,easy to debug
     *
     * @return upload path
     */
    public static String getUploadPath() {
        return "";
    }

    /**
     * create folder if the folder is not exist
     *
     * @param userName : current operator's userName
     * @return folder path
     */
    public static String createFolder(String userName) throws Exception {
        LogUtil.d("createFolder", userName);
        try {
            File file = new File( userName);
            if (!file.exists())
                file.mkdir();
            return file.getAbsolutePath() + "/";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * remove file
     *
     * @param path : file path
     */
    public static void remove(String path) {

        File f = new File(path);
        if (f.exists())
            f.delete();

    }

    public static boolean saveFile(String path, String content) throws IOException {
        File f = new File(path);
        if (!f.exists()) {
            f.createNewFile();
        }

        BufferedWriter output = new BufferedWriter(new FileWriter(f));
        output.write(content);
        output.flush();
        output.close();
        return true;
    }

    public static boolean saveFile(File file, String content) {
        BufferedWriter output = null;
        try {
            output = new BufferedWriter(new FileWriter(file));
            output.write(content);
            output.flush();

            return true;
        } catch (IOException e) {
            Log.e("FileUtil", "保存文件出错", e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                }
            }
        }

        return false;
    }

    /**
     * 复制单个文件的工具文法
     *
     * @param srcFilePath
     * @param destFilePath
     * @throws IOException
     */
    public static void copyFile(String srcFilePath, String destFilePath)
            throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        byte[] buffer = null;
        try {
            fis = new FileInputStream(srcFilePath);
            fos = new FileOutputStream(destFilePath);
            buffer = new byte[1024];
            int count = 0;
            while ((count = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, buffer.length);
            }
        } finally {
            if (fis != null)
                fis.close();
            if (fos != null)
                fos.close();
            if (buffer != null)
                buffer = null;
        }
    }

    public static void mkdirs(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists())
            folder.mkdirs();

    }

    public static String readFromFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }

        return readFromFile(new File(path));
    }

    public static String readFromFile(File file) {
        if (file == null || !file.exists()) {
            return null;
        }

        String content = "";
        String linestr = null;
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(file));
            while ((linestr = input.readLine()) != null) {
                if (content.length() > 0)
                    content += "\n";
                content += linestr;
            }
            input.close();
            return content;
        } catch (FileNotFoundException e) {
            Log.e("FileUtil", "readFromFile error!!!", e);
        } catch (IOException e) {
            Log.e("FileUtil", "readFromFile error!!!", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return null;
    }

    public static boolean hasFile(String path) {
        File file = new File(path);
        return file.exists();
    }

//	public static String getCacheSize(){
//		long size = StringFileCache.getSize() + getImageSize();
//		long size1 = size / 1024;
//		size /= 1024;//kb
//		String sizeName = null;
//		if(size > 1024){
//			size /= 1024;
//			sizeName = size + "." + (size1/1024) + "MB";
//		}else{
//			sizeName = size + "KB";
//		}
//		return sizeName;
//	}
//
//	public static long getImageSize(){
//		File dir = StorageUtils.getOwnCacheDirectory(ReaderApplication.getInstance(), "imageloader/Cache");
//		File[] files = dir.listFiles();
//		if(files == null || files.length == 0)
//			return 0;
//
//		long size = 0;
//		for (File file : files) {
//			size += file.length();
//		}
//
//		return size;
//	}


    public static String ReadFromfile(Context context, String fileName) {
        StringBuilder returnString = new StringBuilder();
        InputStream fIn = null;
        InputStreamReader isr = null;
        BufferedReader input = null;
        try {
            fIn = context.getResources().getAssets()
                    .open(fileName, Context.MODE_PRIVATE);
            isr = new InputStreamReader(fIn);
            input = new BufferedReader(isr);
            String line = "";
            while ((line = input.readLine()) != null) {
                returnString.append(line);
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                if (isr != null)
                    isr.close();
                if (fIn != null)
                    fIn.close();
                if (input != null)
                    input.close();
            } catch (Exception e2) {
                e2.getMessage();
            }
        }
        return returnString.toString();
    }

    /**
     * 获取应用目录下的文件，/data/data/<appName>/<fileName>
     *
     * @param context
     * @param fileName
     * @return
     */
    public static File getDataFile(Context context, String fileName) {
        String path = context.getFilesDir().getAbsolutePath();
        String fileFullPath = path + "/" + fileName;
        return new File(fileFullPath);
    }

    public static void saveException(Throwable ex, SimpleArrayMap<String, String> option) {
        if (ex == null) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        if (option != null && option.size() > 0) {
            int len = option.size();
            for (int i = 0; i < len; i++) {
                String key = option.keyAt(i);
                String value = option.valueAt(i);
                sb.append(key);
                sb.append("===");
                sb.append(value);
                sb.append("\n");
            }
        }

        sb.append("orderId");
        sb.append("===");
//        sb.append(MEETApplication.getInstance());
        sb.append("\n");

        FileOutputStream fos = null;
        try {
            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            printWriter.close();

            String result = writer.toString();
            sb.append(result);
            long timestamp = System.currentTimeMillis();

            String fileName = Constants.CRASH_FILE_NAME_PREFFIX + timestamp + ".log";

            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String filePath = Constants.FILE_DIR_CRASH + fileName;
                File dir = new File(Constants.FILE_DIR_CRASH);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                fos = new FileOutputStream(filePath);

                fos.write(sb.toString().getBytes());
            }

            LogUtil.loge(TAG, "Crash error!!!!", ex);
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    Log.e(TAG, "an error occured while cloase fileout stream...", e);
                }
            }
        }
    }
}
