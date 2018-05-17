package com.zc.dem.common.util;

import java.io.UnsupportedEncodingException;

import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.CryptoException;

/**
 * AES加解密类
 * @author Joehart
 * @version 1.0
 */
public class AES128Util
{
    /**
     * 加密
     * 
     * @param sSrc
     *            待加密的内容
     * @param raw
     *            密钥
     * @return 加密后的密文
     * @throws UnsupportedEncodingException 
     * @throws Exception
     */
    public static String Encrypt(String plaintext, String textKey, String charsetName)
        throws UnsupportedEncodingException
    {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        byte[] plaintBytes = plaintext.getBytes(charsetName == null ? "ISO8859-1" : charsetName);
        byte[] keyBytes = textKey.getBytes(charsetName == null ? "ISO8859-1" : charsetName);
        if (keyBytes.length * 8 != 128)
        {
            throw new RuntimeException("key must be 128 bit !");
        }
        return aesCipherService.encrypt(plaintBytes, keyBytes).toHex();
    }
    
    /**
     * 解密
     * @param sSrc
     *            待解密的内容
     * @param raw
     *            密钥
     * @return 解密后的明文
     * @throws UnsupportedEncodingException 
     * @throws CryptoException 
     * @throws Exception
     */
    
    public static String Decrypt(String ciphertext, String textKey, String charsetName)
        throws CryptoException, UnsupportedEncodingException
    {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        byte[] cipherBytes = Hex.decode(ciphertext);
        byte[] keyBytes = textKey.getBytes(charsetName == null ? "ISO8859-1" : charsetName);
        if (keyBytes.length * 8 != 128)
        {
            throw new RuntimeException("key must be 128 bit !");
        }
        return new String(aesCipherService.decrypt(cipherBytes, keyBytes).getBytes(), charsetName);
    }
    
    public static void main(String[] args){
        try
        {
            System.out.println(Encrypt("sync", "0123456789ABCDEF", "utf-8"));
            String sync = "eef4638636e70fc5d8a33ae597177c39e8041c3e4957ec25019f92de69130509";
            System.out.println(Decrypt(sync, "0123456789ABCDEF", "utf-8"));
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
