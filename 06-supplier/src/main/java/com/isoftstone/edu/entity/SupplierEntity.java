package com.isoftstone.edu.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SupplierEntity {
    private String supplierId;

    private String supplierShortname;

    private String supplierName;

    private String legalPerson;

    private String financing;

    private String businessContacter;

    private String telephone;

    private String address;

    private String openBank;

    private String bankCode;

    private String businessLicense;

    private String taxCertificate;

    private Integer taxpayerProperty;

    private String businessScope;

    private String companyProfile;

    private String servicePromise;

    private String applicationOption;

    private Date certificateTime;

    private Date createdTime;

    private Date modifiedTime;

    public SupplierEntity() {};
    
	public SupplierEntity(String supplierId, String supplierShortname, String supplierName, String legalPerson,
			String financing, String businessContacter, String telephone, String address, String openBank,
			String bankCode, String businessLicense, String taxCertificate, Integer taxpayerProperty,
			String businessScope, String companyProfile, String servicePromise, String applicationOption,
			Date certificateTime, Date createdTime, Date modifiedTime) {
		super();
		this.supplierId = supplierId;
		this.supplierShortname = supplierShortname;
		this.supplierName = supplierName;
		this.legalPerson = legalPerson;
		this.financing = financing;
		this.businessContacter = businessContacter;
		this.telephone = telephone;
		this.address = address;
		this.openBank = openBank;
		this.bankCode = bankCode;
		this.businessLicense = businessLicense;
		this.taxCertificate = taxCertificate;
		this.taxpayerProperty = taxpayerProperty;
		this.businessScope = businessScope;
		this.companyProfile = companyProfile;
		this.servicePromise = servicePromise;
		this.applicationOption = applicationOption;
		this.certificateTime = certificateTime;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getSupplierShortname() {
        return supplierShortname;
    }

    public void setSupplierShortname(String supplierShortname) {
        this.supplierShortname = supplierShortname == null ? null : supplierShortname.trim();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public String getFinancing() {
        return financing;
    }

    public void setFinancing(String financing) {
        this.financing = financing == null ? null : financing.trim();
    }

    public String getBusinessContacter() {
        return businessContacter;
    }

    public void setBusinessContacter(String businessContacter) {
        this.businessContacter = businessContacter == null ? null : businessContacter.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank == null ? null : openBank.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public String getTaxCertificate() {
        return taxCertificate;
    }

    public void setTaxCertificate(String taxCertificate) {
        this.taxCertificate = taxCertificate == null ? null : taxCertificate.trim();
    }

    public Integer getTaxpayerProperty() {
        return taxpayerProperty;
    }

    public void setTaxpayerProperty(Integer taxpayerProperty) {
        this.taxpayerProperty = taxpayerProperty;
    }

    public String getBusinessScope() {
        return businessScope;
    }

    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope == null ? null : businessScope.trim();
    }

    public String getCompanyProfile() {
        return companyProfile;
    }

    public void setCompanyProfile(String companyProfile) {
        this.companyProfile = companyProfile == null ? null : companyProfile.trim();
    }

    public String getServicePromise() {
        return servicePromise;
    }

    public void setServicePromise(String servicePromise) {
        this.servicePromise = servicePromise == null ? null : servicePromise.trim();
    }

    public String getApplicationOption() {
        return applicationOption;
    }

    public void setApplicationOption(String applicationOption) {
        this.applicationOption = applicationOption == null ? null : applicationOption.trim();
    }
    
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getCertificateTime() {
        return certificateTime;
    }

    public void setCertificateTime(Date certificateTime) {
        this.certificateTime = certificateTime;
    }
    
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}