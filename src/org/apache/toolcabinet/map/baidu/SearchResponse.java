package org.apache.toolcabinet.map.baidu;

import java.util.List;

public class SearchResponse {

		private String status;
		private String message;
		private String result_type;
		private List<Result> results;

		// 这两个字段为csv写入时需要
		private String organizationId;
		private String nameZh;

		public String getOrganizationId() {
				return organizationId;
		}

		public void setOrganizationId(String organizationId) {
				this.organizationId = organizationId;
		}

		public String getNameZh() {
				return nameZh;
		}

		public void setNameZh(String nameZh) {
				this.nameZh = nameZh;
		}

		public String getStatus() {
				return status;
		}

		public void setStatus(String status) {
				this.status = status;
		}

		public String getMessage() {
				return message;
		}

		public void setMessage(String message) {
				this.message = message;
		}

		public String getResult_type() {
				return result_type;
		}

		public void setResult_type(String result_type) {
				this.result_type = result_type;
		}

		public List<Result> getResults() {
				return results;
		}

		public void setResults(List<Result> results) {
				this.results = results;
		}

		@Override
		public String toString() {
				return "SearchResponse{" +
								"status='" + status + '\'' +
								", message='" + message + '\'' +
								", result_type='" + result_type + '\'' +
								", results=" + results +
								", organizationId='" + organizationId + '\'' +
								", nameZh='" + nameZh + '\'' +
								'}';
		}
}
