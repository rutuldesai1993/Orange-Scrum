package com.example.rutuldesai.orangescrum;

public class Project {

    private int serial;
    private String ProjectName ;
    private String ProjectManager ;
    private String Department ;
    private String Status ;
    private int PlannedHours ;
    private String SpentHours ;
    private int duration ;
    private float variation ;
    private String ShortName;
    private String ProjectType;
    private String CreatedBy;
    private String StartDate;
    private String CreatedDate;
    private String EndDate;
    private float Progress;
    private float ActualProgress;
    public Project(int serial,String ProjectName,String ProjectManager,
                   String Department,String Status,int PlannedHours,
                   String SpentHours, int duration,float variation,
                   String ShortName,String ProjectType,String CreatedBy,
                   String StartDate,String CreatedDate,String EndDate,
                   float Progress,float ActualProgress){
        this.serial=serial;
        this.ProjectName=ProjectName;
        this.ProjectManager=ProjectManager;
        this.Department=Department;
        this.Status=Status;
        this.PlannedHours=PlannedHours;
        this.SpentHours=SpentHours;
        this.duration=duration;
        this.variation=variation;
        this.ShortName = ShortName;
        this.ProjectType = ProjectType;
        this.CreatedBy=CreatedBy;
        this.StartDate=StartDate;
        this.CreatedDate=CreatedDate;
        this.EndDate=EndDate;
        this.Progress=Progress;
        this.ActualProgress=ActualProgress;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        this.ShortName = shortName;
    }

    public String getProjectType() {
        return ProjectType;
    }

    public void setProjectType(String projectType) {
        this.ProjectType = projectType;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        this.CreatedBy = createdBy;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        this.StartDate = startDate;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        this.CreatedDate = createdDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        this.EndDate = endDate;
    }

    public float getProgress() {
        return Progress;
    }

    public void setProgress(float progress) {
        this.Progress = progress;
    }

    public float getActualProgress() {
        return ActualProgress;
    }

    public void setActualProgress(float actualProgress) {
        this.ActualProgress = actualProgress;
    }

    public Project(){
    }


    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getProjectManager() {
        return ProjectManager;
    }

    public void setProjectManager(String projectManager) {
        ProjectManager = projectManager;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public int getPlannedHours() {
        return PlannedHours;
    }

    public void setPlannedHours(int plannedHours) {
        PlannedHours = plannedHours;
    }

    public String getSpentHours() {
        return SpentHours;
    }

    public void setSpentHours(String spentHours) {
        SpentHours = spentHours;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getVariation() {
        return variation;
    }

    public void setVariation(float variation) {
        this.variation = variation;
    }
}
