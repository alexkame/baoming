package com.thinkgem.jeesite.wx.untils;

import java.awt.image.BufferedImage;

public class ImageRotateUtil {

    /**
     * 图像右转90度
     * 
     * @param bi
     * @return
     */
    public static BufferedImage rotate(BufferedImage bi, String rotate) {
        if ("-1".equals(rotate)) {
            return rotate90DX(bi);
        }
        if ("1".equals(rotate)) {
            return rotate90SX(bi);
        }
        if ("2".equals(rotate)) {
            return rotate180X(bi);
        }
        return bi;
    }

    /**
     * 图像旋转180度
     * 
     * @param bi
     *            图像缓冲流
     * @return
     */
    public static BufferedImage rotate180X(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        BufferedImage biFlip = new BufferedImage(width, height, bi.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // System.out.println("======= " + i + " ====== " + j);
                // System.out.println(biFlip.getWidth() + " : " +
                // biFlip.getHeight());
                biFlip.setRGB(width - i - 1, height - j - 1, bi.getRGB(i, j));
            }
        }
        return biFlip;
    }

    /**
     * 图像左转90度
     * 
     * @param bi
     * @return
     */
    public static BufferedImage rotate90DX(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // System.out.println("dW: " + j + " dH: " + (width -i - 1) +
                // "  ===  " + i + "," + j);
                biFlip.setRGB(j, width - i - 1, bi.getRGB(i, j));
            }
        }
        return biFlip;
    }

    /**
     * 图像右转90度
     * 
     * @param bi
     * @return
     */
    public static BufferedImage rotate90SX(BufferedImage bi) {
        int width = bi.getWidth();
        int height = bi.getHeight();
        BufferedImage biFlip = new BufferedImage(height, width, bi.getType());
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // System.out.println("dW:" + i + "dH:" + (height - j - 1) +
                // " ======== " + i + "," + j);
                biFlip.setRGB(height - j - 1, i, bi.getRGB(i, j));
            }
        }
        return biFlip;
    }
}
