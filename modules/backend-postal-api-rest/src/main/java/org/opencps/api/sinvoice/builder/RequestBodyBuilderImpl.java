package org.opencps.api.sinvoice.builder;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;
import java.util.Properties;

/**
 * @author trungnt
 *
 */
public class RequestBodyBuilderImpl extends RequestBodyBuilder {

	private JSONObject data;
	private JSONObject generalInvoiceInfo;
	private JSONObject buyerInfo;
	private JSONObject sellerInfo;
	private JSONArray extAttribute;
	private JSONArray payments;
	private JSONObject deliveryInfo;
	private JSONArray itemInfo;
	private JSONArray discountItemInfo;
	private JSONObject summarizeInfo;
	private JSONArray taxBreakdowns;
	private JSONArray metadata;
	private JSONArray customFields;
	private JSONArray meterReading;
	private JSONObject invoiceFile;

	public RequestBodyBuilderImpl build() {
		JSONObject data = JSONFactoryUtil.createJSONObject();

		if (getGeneralInvoiceInfo() != null) {
			data.put("generalInvoiceInfo", getGeneralInvoiceInfo());
		}

		if (getBuyerInfo() != null) {
			data.put("buyerInfo", getBuyerInfo());
		}

		if (getSellerInfo() != null) {
			data.put("sellerInfo", getSellerInfo());
		}

		if (getExtAttribute() != null) {
			data.put("extAttribute", getExtAttribute());
		}

		if (getPayments() != null) {
			data.put("payments", getPayments());
		}

		if (getDeliveryInfo() != null) {
			data.put("deliveryInfo", getDeliveryInfo());
		}

		if (getItemInfo() != null) {
			data.put("itemInfo", getItemInfo());
		}

		if (getDiscountItemInfo() != null) {
			data.put("discountItemInfo", getDiscountItemInfo());
		}

		if (getSummarizeInfo() != null) {
			data.put("summarizeInfo", getSummarizeInfo());
		}

		if (getTaxBreakdowns() != null) {
			data.put("taxBreakdowns", getTaxBreakdowns());
		}

		if (getMetadata() != null) {
			data.put("metadata", getMetadata());
		}

		if (getCustomFields() != null) {
			data.put("customFields", getCustomFields());
		}

		if (getMeterReading() != null) {
			data.put("meterReading", getMeterReading());
		}

		if (getInvoiceFile() != null) {
			data.put("invoiceFile", getInvoiceFile());
		}

		this.setData(data);

		return this;
	}

	public RequestBodyBuilderImpl generalInvoiceInfo(Properties properties) {
		if (properties != null) {
			JSONObject generalInvoiceInfo = JSONFactoryUtil.createJSONObject();
			properties.forEach((k, v) -> generalInvoiceInfo.put((String) k, v));
			this.setGeneralInvoiceInfo(generalInvoiceInfo);
		}

		return this;
	}

	public RequestBodyBuilderImpl buyerInfo(Properties properties) {
		if (properties != null) {
			JSONObject buyerInfo = JSONFactoryUtil.createJSONObject();
			properties.forEach((k, v) -> buyerInfo.put((String) k, v));
			this.setBuyerInfo(buyerInfo);
		}

		return this;
	}

	public RequestBodyBuilderImpl deliveryInfo(Properties properties) {
		if (properties != null) {
			JSONObject deliveryInfo = JSONFactoryUtil.createJSONObject();
			properties.forEach((k, v) -> deliveryInfo.put((String) k, v));
			this.setDeliveryInfo(deliveryInfo);
		}

		return this;
	}

	public RequestBodyBuilderImpl invoiceFile(Properties properties) {
		if (properties != null) {
			JSONObject invoiceFile = JSONFactoryUtil.createJSONObject();
			properties.forEach((k, v) -> invoiceFile.put((String) k, v));
			this.setInvoiceFile(invoiceFile);
		}

		return this;
	}

	public RequestBodyBuilderImpl summarizeInfo(Properties properties) {
		if (properties != null) {
			JSONObject summarizeInfo = JSONFactoryUtil.createJSONObject();
			properties.forEach((k, v) -> summarizeInfo.put((String) k, v));
			this.setSummarizeInfo(summarizeInfo);
		}

		return this;
	}

	public RequestBodyBuilderImpl sellerInfo(Properties properties) {
		if (properties != null) {
			JSONObject sellerInfo = JSONFactoryUtil.createJSONObject();
			properties.forEach((k, v) -> sellerInfo.put((String) k, v));
			this.setSellerInfo(sellerInfo);
		}

		return this;
	}

	public RequestBodyBuilderImpl extAttribute(List<Properties> lstProperties) {
		if (lstProperties != null) {
			JSONArray extAttributes = JSONFactoryUtil.createJSONArray();
			for (Properties properties : lstProperties) {
				JSONObject extAttribute = JSONFactoryUtil.createJSONObject();
				properties.forEach((k, v) -> extAttribute.put((String) k, v));
				extAttributes.put(extAttribute);
			}

			this.setExtAttribute(extAttributes);
		}

		return this;
	}

	public RequestBodyBuilderImpl payments(List<Properties> lstProperties) {
		if (lstProperties != null) {
			JSONArray payments = JSONFactoryUtil.createJSONArray();
			for (Properties properties : lstProperties) {
				JSONObject payment = JSONFactoryUtil.createJSONObject();
				properties.forEach((k, v) -> payment.put((String) k, v));
				payments.put(payment);
			}

			this.setPayments(payments);
		}

		return this;
	}

	public RequestBodyBuilderImpl itemInfo(List<Properties> lstProperties) {
		if (lstProperties != null) {
			JSONArray itemInfos = JSONFactoryUtil.createJSONArray();
			for (Properties properties : lstProperties) {
				JSONObject itemInfo = JSONFactoryUtil.createJSONObject();
				properties.forEach((k, v) -> itemInfo.put((String) k, v));
				itemInfos.put(itemInfo);
			}

			this.setItemInfo(itemInfos);
		}

		return this;
	}

	public RequestBodyBuilderImpl discountItemInfo(List<Properties> lstProperties) {
		if (lstProperties != null) {
			JSONArray discountItemInfos = JSONFactoryUtil.createJSONArray();
			for (Properties properties : lstProperties) {
				JSONObject discountItemInfo = JSONFactoryUtil.createJSONObject();
				properties.forEach((k, v) -> discountItemInfo.put((String) k, v));
				discountItemInfos.put(discountItemInfo);
			}

			this.setDiscountItemInfo(discountItemInfos);
		}

		return this;
	}

	public RequestBodyBuilderImpl taxBreakdowns(List<Properties> lstProperties) {
		if (lstProperties != null) {
			JSONArray taxBreakdowns = JSONFactoryUtil.createJSONArray();
			for (Properties properties : lstProperties) {
				JSONObject taxBreakdown = JSONFactoryUtil.createJSONObject();
				properties.forEach((k, v) -> taxBreakdown.put((String) k, v));
				taxBreakdowns.put(taxBreakdown);
			}

			this.setTaxBreakdowns(taxBreakdowns);
		}

		return this;
	}

	public RequestBodyBuilderImpl metadata(List<Properties> lstProperties) {
		if (lstProperties != null) {
			JSONArray metadatas = JSONFactoryUtil.createJSONArray();
			for (Properties properties : lstProperties) {
				JSONObject metadata = JSONFactoryUtil.createJSONObject();
				properties.forEach((k, v) -> metadata.put((String) k, v));
				metadatas.put(metadata);
			}

			this.setMetadata(metadatas);
		}

		return this;
	}

	public RequestBodyBuilderImpl customFields(List<Properties> lstProperties) {
		if (lstProperties != null) {
			JSONArray customFields = JSONFactoryUtil.createJSONArray();
			for (Properties properties : lstProperties) {
				JSONObject customField = JSONFactoryUtil.createJSONObject();
				properties.forEach((k, v) -> customField.put((String) k, v));
				customFields.put(customField);
			}

			this.setCustomFields(customFields);
		}

		return this;
	}

	public RequestBodyBuilderImpl meterReadings(List<Properties> lstProperties) {
		if (lstProperties != null) {
			JSONArray meterReadings = JSONFactoryUtil.createJSONArray();
			for (Properties properties : lstProperties) {
				JSONObject meterReading = JSONFactoryUtil.createJSONObject();
				properties.forEach((k, v) -> meterReading.put((String) k, v));
				meterReadings.put(meterReading);
			}

			this.setMeterReading(meterReadings);
		}

		return this;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public JSONObject getGeneralInvoiceInfo() {
		return generalInvoiceInfo;
	}

	public void setGeneralInvoiceInfo(JSONObject generalInvoiceInfo) {
		this.generalInvoiceInfo = generalInvoiceInfo;
	}

	public JSONObject getBuyerInfo() {
		return buyerInfo;
	}

	public void setBuyerInfo(JSONObject buyerInfo) {
		this.buyerInfo = buyerInfo;
	}

	public JSONObject getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(JSONObject sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public JSONArray getExtAttribute() {
		return extAttribute;
	}

	public void setExtAttribute(JSONArray extAttribute) {
		this.extAttribute = extAttribute;
	}

	public JSONObject getDeliveryInfo() {
		return deliveryInfo;
	}

	public void setDeliveryInfo(JSONObject deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}

	public JSONObject getSummarizeInfo() {
		return summarizeInfo;
	}

	public void setSummarizeInfo(JSONObject summarizeInfo) {
		this.summarizeInfo = summarizeInfo;
	}

	public JSONObject getInvoiceFile() {
		return invoiceFile;
	}

	public void setInvoiceFile(JSONObject invoiceFile) {
		this.invoiceFile = invoiceFile;
	}

	public JSONArray getPayments() {
		return payments;
	}

	public void setPayments(JSONArray payments) {
		this.payments = payments;
	}

	public JSONArray getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(JSONArray itemInfo) {
		this.itemInfo = itemInfo;
	}

	public JSONArray getDiscountItemInfo() {
		return discountItemInfo;
	}

	public void setDiscountItemInfo(JSONArray discountItemInfo) {
		this.discountItemInfo = discountItemInfo;
	}

	public JSONArray getTaxBreakdowns() {
		return taxBreakdowns;
	}

	public void setTaxBreakdowns(JSONArray taxBreakdowns) {
		this.taxBreakdowns = taxBreakdowns;
	}

	public JSONArray getMetadata() {
		return metadata;
	}

	public void setMetadata(JSONArray metadata) {
		this.metadata = metadata;
	}

	public JSONArray getCustomFields() {
		return customFields;
	}

	public void setCustomFields(JSONArray customFields) {
		this.customFields = customFields;
	}

	public JSONArray getMeterReading() {
		return meterReading;
	}

	public void setMeterReading(JSONArray meterReading) {
		this.meterReading = meterReading;
	}

}
