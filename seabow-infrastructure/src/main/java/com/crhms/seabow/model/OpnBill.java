package com.crhms.seabow.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "OPN_BILL", schema = "BMITEST")
public class OpnBill {
    private String id;
    private String hospitalId;
    private String month;
    private long versionNo;
    private String hisid;
    private String billNo;
    private Time billdate;
    private String patientId;
    private String patientName;
    private Time admissionDate;
    private Time dischargeDate;
    private String admissionNumber;
    private String admissionDiseaseName;
    private String diseaseName;
    private String claimType;
    private String claimName;
    private String benefitType;
    private String benefitName;
    private String benefitGroupId;
    private String benefitGroupName;
    private String bmino;
    private String tablePar;
    private String bmiCode;
    private Long bmiConveredAmount;
    private Long bmiNopay;
    private Long ruleBit;
    private String ruleName;
    private String deptName;
    private String physicianName;
    private Time generatedDate;
    private String remrk;
    private String rebackReason;
    private String trickState;
    private String isRetrick;
    private String isApproval;
    private String processState;
    private String remarkContent;
    private String rebackReasonContent;
    private String drgsReject;
    private String idNumber;
    private Long billNopay;
    private Long detailNopay;
    private String rejectReason;
    private String hospitalName;
    private String bmiName;
    private Long firstBillNopay;
    private String physicianId;
    private String anotherplaceAuditType;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "HOSPITAL_ID")
    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    @Basic
    @Column(name = "MONTH")
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    @Basic
    @Column(name = "VERSION_NO")
    public long getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(long versionNo) {
        this.versionNo = versionNo;
    }

    @Basic
    @Column(name = "HISID")
    public String getHisid() {
        return hisid;
    }

    public void setHisid(String hisid) {
        this.hisid = hisid;
    }

    @Basic
    @Column(name = "BILL_NO")
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    @Basic
    @Column(name = "BILLDATE")
    public Time getBilldate() {
        return billdate;
    }

    public void setBilldate(Time billdate) {
        this.billdate = billdate;
    }

    @Basic
    @Column(name = "PATIENT_ID")
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "PATIENT_NAME")
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    @Basic
    @Column(name = "ADMISSION_DATE")
    public Time getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Time admissionDate) {
        this.admissionDate = admissionDate;
    }

    @Basic
    @Column(name = "DISCHARGE_DATE")
    public Time getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Time dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    @Basic
    @Column(name = "ADMISSION_NUMBER")
    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    @Basic
    @Column(name = "ADMISSION_DISEASE_NAME")
    public String getAdmissionDiseaseName() {
        return admissionDiseaseName;
    }

    public void setAdmissionDiseaseName(String admissionDiseaseName) {
        this.admissionDiseaseName = admissionDiseaseName;
    }

    @Basic
    @Column(name = "DISEASE_NAME")
    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    @Basic
    @Column(name = "CLAIM_TYPE")
    public String getClaimType() {
        return claimType;
    }

    public void setClaimType(String claimType) {
        this.claimType = claimType;
    }

    @Basic
    @Column(name = "CLAIM_NAME")
    public String getClaimName() {
        return claimName;
    }

    public void setClaimName(String claimName) {
        this.claimName = claimName;
    }

    @Basic
    @Column(name = "BENEFIT_TYPE")
    public String getBenefitType() {
        return benefitType;
    }

    public void setBenefitType(String benefitType) {
        this.benefitType = benefitType;
    }

    @Basic
    @Column(name = "BENEFIT_NAME")
    public String getBenefitName() {
        return benefitName;
    }

    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName;
    }

    @Basic
    @Column(name = "BENEFIT_GROUP_ID")
    public String getBenefitGroupId() {
        return benefitGroupId;
    }

    public void setBenefitGroupId(String benefitGroupId) {
        this.benefitGroupId = benefitGroupId;
    }

    @Basic
    @Column(name = "BENEFIT_GROUP_NAME")
    public String getBenefitGroupName() {
        return benefitGroupName;
    }

    public void setBenefitGroupName(String benefitGroupName) {
        this.benefitGroupName = benefitGroupName;
    }

    @Basic
    @Column(name = "BMINO")
    public String getBmino() {
        return bmino;
    }

    public void setBmino(String bmino) {
        this.bmino = bmino;
    }

    @Basic
    @Column(name = "TABLE_PAR")
    public String getTablePar() {
        return tablePar;
    }

    public void setTablePar(String tablePar) {
        this.tablePar = tablePar;
    }

    @Basic
    @Column(name = "BMI_CODE")
    public String getBmiCode() {
        return bmiCode;
    }

    public void setBmiCode(String bmiCode) {
        this.bmiCode = bmiCode;
    }

    @Basic
    @Column(name = "BMI_CONVERED_AMOUNT")
    public Long getBmiConveredAmount() {
        return bmiConveredAmount;
    }

    public void setBmiConveredAmount(Long bmiConveredAmount) {
        this.bmiConveredAmount = bmiConveredAmount;
    }

    @Basic
    @Column(name = "BMI_NOPAY")
    public Long getBmiNopay() {
        return bmiNopay;
    }

    public void setBmiNopay(Long bmiNopay) {
        this.bmiNopay = bmiNopay;
    }

    @Basic
    @Column(name = "RULE_BIT")
    public Long getRuleBit() {
        return ruleBit;
    }

    public void setRuleBit(Long ruleBit) {
        this.ruleBit = ruleBit;
    }

    @Basic
    @Column(name = "RULE_NAME")
    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    @Basic
    @Column(name = "DEPT_NAME")
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Basic
    @Column(name = "PHYSICIAN_NAME")
    public String getPhysicianName() {
        return physicianName;
    }

    public void setPhysicianName(String physicianName) {
        this.physicianName = physicianName;
    }

    @Basic
    @Column(name = "GENERATED_DATE")
    public Time getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(Time generatedDate) {
        this.generatedDate = generatedDate;
    }

    @Basic
    @Column(name = "REMRK")
    public String getRemrk() {
        return remrk;
    }

    public void setRemrk(String remrk) {
        this.remrk = remrk;
    }

    @Basic
    @Column(name = "REBACK_REASON")
    public String getRebackReason() {
        return rebackReason;
    }

    public void setRebackReason(String rebackReason) {
        this.rebackReason = rebackReason;
    }

    @Basic
    @Column(name = "TRICK_STATE")
    public String getTrickState() {
        return trickState;
    }

    public void setTrickState(String trickState) {
        this.trickState = trickState;
    }

    @Basic
    @Column(name = "IS_RETRICK")
    public String getIsRetrick() {
        return isRetrick;
    }

    public void setIsRetrick(String isRetrick) {
        this.isRetrick = isRetrick;
    }

    @Basic
    @Column(name = "IS_APPROVAL")
    public String getIsApproval() {
        return isApproval;
    }

    public void setIsApproval(String isApproval) {
        this.isApproval = isApproval;
    }

    @Basic
    @Column(name = "PROCESS_STATE")
    public String getProcessState() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
    }

    @Basic
    @Column(name = "REMARK_CONTENT")
    public String getRemarkContent() {
        return remarkContent;
    }

    public void setRemarkContent(String remarkContent) {
        this.remarkContent = remarkContent;
    }

    @Basic
    @Column(name = "REBACK_REASON_CONTENT")
    public String getRebackReasonContent() {
        return rebackReasonContent;
    }

    public void setRebackReasonContent(String rebackReasonContent) {
        this.rebackReasonContent = rebackReasonContent;
    }

    @Basic
    @Column(name = "DRGS_REJECT")
    public String getDrgsReject() {
        return drgsReject;
    }

    public void setDrgsReject(String drgsReject) {
        this.drgsReject = drgsReject;
    }

    @Basic
    @Column(name = "ID_NUMBER")
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    @Column(name = "BILL_NOPAY")
    public Long getBillNopay() {
        return billNopay;
    }

    public void setBillNopay(Long billNopay) {
        this.billNopay = billNopay;
    }

    @Basic
    @Column(name = "DETAIL_NOPAY")
    public Long getDetailNopay() {
        return detailNopay;
    }

    public void setDetailNopay(Long detailNopay) {
        this.detailNopay = detailNopay;
    }

    @Basic
    @Column(name = "REJECT_REASON")
    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    @Basic
    @Column(name = "HOSPITAL_NAME")
    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    @Basic
    @Column(name = "BMI_NAME")
    public String getBmiName() {
        return bmiName;
    }

    public void setBmiName(String bmiName) {
        this.bmiName = bmiName;
    }

    @Basic
    @Column(name = "FIRST_BILL_NOPAY")
    public Long getFirstBillNopay() {
        return firstBillNopay;
    }

    public void setFirstBillNopay(Long firstBillNopay) {
        this.firstBillNopay = firstBillNopay;
    }

    @Basic
    @Column(name = "PHYSICIAN_ID")
    public String getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(String physicianId) {
        this.physicianId = physicianId;
    }

    @Basic
    @Column(name = "ANOTHERPLACE_AUDIT_TYPE")
    public String getAnotherplaceAuditType() {
        return anotherplaceAuditType;
    }

    public void setAnotherplaceAuditType(String anotherplaceAuditType) {
        this.anotherplaceAuditType = anotherplaceAuditType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpnBill opnBill = (OpnBill) o;
        return versionNo == opnBill.versionNo &&
                Objects.equals(id, opnBill.id) &&
                Objects.equals(hospitalId, opnBill.hospitalId) &&
                Objects.equals(month, opnBill.month) &&
                Objects.equals(hisid, opnBill.hisid) &&
                Objects.equals(billNo, opnBill.billNo) &&
                Objects.equals(billdate, opnBill.billdate) &&
                Objects.equals(patientId, opnBill.patientId) &&
                Objects.equals(patientName, opnBill.patientName) &&
                Objects.equals(admissionDate, opnBill.admissionDate) &&
                Objects.equals(dischargeDate, opnBill.dischargeDate) &&
                Objects.equals(admissionNumber, opnBill.admissionNumber) &&
                Objects.equals(admissionDiseaseName, opnBill.admissionDiseaseName) &&
                Objects.equals(diseaseName, opnBill.diseaseName) &&
                Objects.equals(claimType, opnBill.claimType) &&
                Objects.equals(claimName, opnBill.claimName) &&
                Objects.equals(benefitType, opnBill.benefitType) &&
                Objects.equals(benefitName, opnBill.benefitName) &&
                Objects.equals(benefitGroupId, opnBill.benefitGroupId) &&
                Objects.equals(benefitGroupName, opnBill.benefitGroupName) &&
                Objects.equals(bmino, opnBill.bmino) &&
                Objects.equals(tablePar, opnBill.tablePar) &&
                Objects.equals(bmiCode, opnBill.bmiCode) &&
                Objects.equals(bmiConveredAmount, opnBill.bmiConveredAmount) &&
                Objects.equals(bmiNopay, opnBill.bmiNopay) &&
                Objects.equals(ruleBit, opnBill.ruleBit) &&
                Objects.equals(ruleName, opnBill.ruleName) &&
                Objects.equals(deptName, opnBill.deptName) &&
                Objects.equals(physicianName, opnBill.physicianName) &&
                Objects.equals(generatedDate, opnBill.generatedDate) &&
                Objects.equals(remrk, opnBill.remrk) &&
                Objects.equals(rebackReason, opnBill.rebackReason) &&
                Objects.equals(trickState, opnBill.trickState) &&
                Objects.equals(isRetrick, opnBill.isRetrick) &&
                Objects.equals(isApproval, opnBill.isApproval) &&
                Objects.equals(processState, opnBill.processState) &&
                Objects.equals(remarkContent, opnBill.remarkContent) &&
                Objects.equals(rebackReasonContent, opnBill.rebackReasonContent) &&
                Objects.equals(drgsReject, opnBill.drgsReject) &&
                Objects.equals(idNumber, opnBill.idNumber) &&
                Objects.equals(billNopay, opnBill.billNopay) &&
                Objects.equals(detailNopay, opnBill.detailNopay) &&
                Objects.equals(rejectReason, opnBill.rejectReason) &&
                Objects.equals(hospitalName, opnBill.hospitalName) &&
                Objects.equals(bmiName, opnBill.bmiName) &&
                Objects.equals(firstBillNopay, opnBill.firstBillNopay) &&
                Objects.equals(physicianId, opnBill.physicianId) &&
                Objects.equals(anotherplaceAuditType, opnBill.anotherplaceAuditType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hospitalId, month, versionNo, hisid, billNo, billdate, patientId, patientName, admissionDate, dischargeDate, admissionNumber, admissionDiseaseName, diseaseName, claimType, claimName, benefitType, benefitName, benefitGroupId, benefitGroupName, bmino, tablePar, bmiCode, bmiConveredAmount, bmiNopay, ruleBit, ruleName, deptName, physicianName, generatedDate, remrk, rebackReason, trickState, isRetrick, isApproval, processState, remarkContent, rebackReasonContent, drgsReject, idNumber, billNopay, detailNopay, rejectReason, hospitalName, bmiName, firstBillNopay, physicianId, anotherplaceAuditType);
    }
}
