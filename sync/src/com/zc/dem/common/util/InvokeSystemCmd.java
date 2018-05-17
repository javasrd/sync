package com.zc.dem.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zc.dem.common.StartUpServlet;
import com.zc.dem.common.constant.DemConstant;
import com.zc.dem.common.constant.DemConstant.osName;

public class InvokeSystemCmd
{
    /** 日志类 */
    private static final Logger log = LoggerFactory.getLogger(StartUpServlet.class);

    public static int exeSysCommand(String filePath)
    {
        int retExec = DemConstant.operateResult.suc;

        String cmd = null;
        String retStr = null;
        
        Runtime run = Runtime.getRuntime();
        String osname = System.getProperty("os.name").toLowerCase();

        if (osname.indexOf(osName.windows) >= 0)
        {
            cmd = "cmd.exe /c " + filePath;
        }
        else if (osname.indexOf(osName.linux) >= 0)
        {
            cmd = "chmod +x " + filePath + "; " + filePath;
        }
        else
        {
            retExec = DemConstant.operateResult.fasle;
            retStr = "Do not support OS:" + osname;
            log.error("execute command failed, command:" + filePath + "\n, information:" + retStr);
            return retExec;
        }
        
        try
        {
            Process pr = run.exec(cmd); //运行cmd命令
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line;
            while ((line = input.readLine()) != null)
            {
                retStr += line;
                retStr += '\n';
            }
            input.close();
            
            //导致当前线程等待，如果必要，一直要等到由该 Process 对象表示的进程已经终止。
            pr.waitFor();
            
            //此 Process 对象表示的子进程的出口值。根据惯例，值 0 表示正常终止。
            if (0 == pr.exitValue())
            {
                retExec = DemConstant.operateResult.suc;
                log.info(retStr);
            }
            else
            {
                retExec = DemConstant.operateResult.fasle;
                log.error(retStr);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        
        return retExec;
    }
    
    //    public static void main(String[] args)
    //    {
    //        exeSysCommand("G:\\test.bat");
    //    }
}
