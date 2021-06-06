package org.apache.toolcabinet.map.gaode;

import java.io.Serializable;
import java.util.List;

/**
 * 高德地图地理编码返回pojo类
 *
 * @Date 2021/4/23
 */
public class GeocodeResponse implements Serializable {
		// 返回结果状态值
		private String status;

		// 返回结果数目
		private String count;

		// 返回状态说明
		private String info;

		// 地理编码信息列表，结果对象列表
		private List<Geocode> geocodes;

		public String getStatus() {
				return status;
		}

		public void setStatus(String status) {
				this.status = status;
		}

		public String getCount() {
				return count;
		}

		public void setCount(String count) {
				this.count = count;
		}

		public String getInfo() {
				return info;
		}

		public void setInfo(String info) {
				this.info = info;
		}

		public List<Geocode> getGeocodes() {
				return geocodes;
		}

		public void setGeocodes(List<Geocode> geocodes) {
				this.geocodes = geocodes;
		}

		@Override
		public String toString() {
				return "GeocodeResponse{" +
								"status='" + status + '\'' +
								", count='" + count + '\'' +
								", info='" + info + '\'' +
								", geocodes=" + geocodes +
								'}';
		}
}
