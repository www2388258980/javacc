package com.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class HttpClientUtil {


		public static String doPost(String url, String jsonstr, String charset) {

				HttpPost httpPost = null;
				String result = null;
				try {
						/**
						 * 4. 发送请求
						 */
						CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(createHttpSSL(false)).build();

						httpPost = new HttpPost(url);
						httpPost.addHeader("Content-Type", "application/xml");
						StringEntity se = new StringEntity(jsonstr);
						se.setContentType("text/json");
						se.setContentEncoding(new BasicHeader("Content-Type", "application/xml"));
						httpPost.setEntity(se);
						HttpResponse response = httpClient.execute(httpPost);
						if (response != null) {
								HttpEntity resEntity = response.getEntity();
								if (resEntity != null) {
										result = EntityUtils.toString(resEntity, charset);
								}
						}
				} catch (Exception ex) {
						ex.printStackTrace();
				}
				return result;
		}

		/**
		 * @param isSSL false - 绕过ssl
		 * @return
		 * @throws NoSuchAlgorithmException
		 * @throws KeyManagementException
		 * @throws KeyStoreException
		 * @throws IOException
		 * @throws CertificateException
		 */
		private static PoolingHttpClientConnectionManager createHttpSSL(boolean isSSL) throws NoSuchAlgorithmException,
						KeyManagementException, KeyStoreException, IOException, CertificateException {
				File creFile = new File("C:\\Users\\Administrator\\Desktop\\my.store");
				//证书库密码, 生成my.store时输入的口令
				String crePwd = "mypassword";
				/**
				 * 1. 创建 SSL上下文对象
				 */
				SSLContext sslContext = null;
				if (!isSSL) {
						sslContext = SSLContext.getInstance("SSLv3");
						// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
						X509TrustManager x509TrustManager = new X509TrustManager() {

								public X509Certificate[] getAcceptedIssuers() {
										return null;
								}


								public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
								}


								public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
								}
						};
						sslContext.init(null, new TrustManager[]{x509TrustManager}, null);
				} else {
						if (null != creFile && creFile.length() > 0) {
								if (null != crePwd) {
										KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
										keyStore.load(new FileInputStream(creFile), crePwd.toCharArray());
										sslContext = SSLContexts.custom().loadTrustMaterial(keyStore, new TrustSelfSignedStrategy()).build();
								} else {
										throw new SSLHandshakeException("密码为空");
								}
						}
				}

				/**
				 * 2. 注册
				 */
				Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
								.register("http", PlainConnectionSocketFactory.INSTANCE)
								.register("https", new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.getDefaultHostnameVerifier()))
								.build();

				/**
				 * 3. SSL注册到连接管理器中
				 */
				PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
				connManager.setMaxTotal(1000);  // 连接池最大连接数
				connManager.setDefaultMaxPerRoute(20);  // 每个路由最大连接数
				return connManager;
		}

}