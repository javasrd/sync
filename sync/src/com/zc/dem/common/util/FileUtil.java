package com.zc.dem.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 文件处理
 * @author Joehart
 * @version 1.0
 */
public class FileUtil
{
    /** 
     * 以行为单位读取文件，常用于读面向行的格式化文件 
     *  
     * @param fileName 
     *            文件名 
     */
    public static String readFileByLines(String fileName)
    {
        File file = new File(fileName);
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            
            StringBuffer str = new StringBuffer();
            // 一次读入一行，直到读入null为文件结束  
            while ((tempString = reader.readLine()) != null)
            {
                str.append(tempString);
            }
            
            return str.toString();
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e1)
                {
                }
            }
        }
        
        return "";
    }
}
