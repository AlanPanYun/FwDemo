//package com.example.fwdemo.api;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class DownloadUtil {
//
//    private static DownloadUtil downloadUtil;
//    private final OkHttpClient okHttpClient;
//
//    public static DownloadUtil get() {
//        if (downloadUtil == null) {
//            downloadUtil = new DownloadUtil();
//        }
//        return downloadUtil;
//    }
//
//    private DownloadUtil() {
//        okHttpClient =null;
//    }
//
//    /**
//     * @param url      下载连接
//     * @param file     储存下载文件
//     * @param listener 下载监听
//     */
//    public void download(String url, final File file, final OnDownloadListener listener) {
//        Request request = new Request.Builder().url(url).build();
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                // 下载失败
//                listener.onDownloadFailed();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                if (response.isSuccessful()) {
//                    InputStream is = null;
//                    byte[] buf = new byte[2048];
//                    int len = 0;
//                    FileOutputStream fos = null;
//                    // 储存下载文件的目录
//                    try {
//                        if (!file.exists()) {
//                            file.createNewFile();
//                        }
//                        is = response.body().byteStream();
//                        long total = response.body().contentLength();
//                        fos = new FileOutputStream(file);
//                        long sum = 0;
//                        while ((len = is.read(buf)) != -1) {
//                            fos.write(buf, 0, len);
//                            sum += len;
//                            int progress = (int) (sum * 1.0f / total * 100);
//                            // 下载中
//                            listener.onDownloading(progress);
//                        }
//                        fos.flush();
//                        // 下载完成
//                        listener.onDownloadSuccess();
//                    } catch (Exception e) {
//                        listener.onDownloadFailed();
//                    } finally {
//                        try {
//                            if (is != null)
//                                is.close();
//                        } catch (IOException e) {
//                        }
//                        try {
//                            if (fos != null)
//                                fos.close();
//                        } catch (IOException e) {
//                        }
//                    }
//                } else {
//                    listener.onDownloadFailed();
//                }
//            }
//        });
//    }
//
//    /**
//     * @param url      下载连接
//     * @param saveDir  储存下载文件的SDCard目录
//     * @param listener 下载监听
//     */
//    public void download(final String url, final String saveDir, String name, final OnDownloadListener listener) {
//        File file = new File(saveDir, name);
//        download(url, file, listener);
//    }
//
//    public interface OnDownloadListener {
//        /**
//         * 下载成功
//         */
//        void onDownloadSuccess();
//
//        /**
//         * @param progress 下载进度
//         */
//        void onDownloading(int progress);
//
//        /**
//         * 下载失败
//         */
//        void onDownloadFailed();
//    }
//}