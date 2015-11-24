/**
 * 
 */
package com.zhanjixun.domain;

import android.media.Image;

/**
 * @author Imissyou
 * @Time  2015年11月24日
 *
 */
public class Logistics {
		// 商品图片
		private Image order_image;
		// 物流状态
		private String logistice_status;
		// 物流公司
		private String logistice_company;
		// 物流编号
		private String logistice_code;
		// 物流电话
		private String logistice_phone;
		
		
		
		/**
		 * @return order_image
		 */
		public Image getOrder_image() {
			return order_image;
		}
		/**
		 * @param order_image 要设置的 order_image
		 */
		public void setOrder_image(Image order_image) {
			this.order_image = order_image;
		}
		/**
		 * @return logistice_status
		 */
		public String getLogistice_status() {
			return logistice_status;
		}
		/**
		 * @param logistice_status 要设置的 logistice_status
		 */
		public void setLogistice_status(String logistice_status) {
			this.logistice_status = logistice_status;
		}
		/**
		 * @return logistice_company
		 */
		public String getLogistice_company() {
			return logistice_company;
		}
		/**
		 * @param logistice_company 要设置的 logistice_company
		 */
		public void setLogistice_company(String logistice_company) {
			this.logistice_company = logistice_company;
		}
		/**
		 * @return logistice_code
		 */
		public String getLogistice_code() {
			return logistice_code;
		}
		/**
		 * @param logistice_code 要设置的 logistice_code
		 */
		public void setLogistice_code(String logistice_code) {
			this.logistice_code = logistice_code;
		}
		/**
		 * @return logistice_phone
		 */
		public String getLogistice_phone() {
			return logistice_phone;
		}
		/**
		 * @param logistice_phone 要设置的 logistice_phone
		 */
		public void setLogistice_phone(String logistice_phone) {
			this.logistice_phone = logistice_phone;
		}
}
