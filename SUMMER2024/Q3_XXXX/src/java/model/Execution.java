/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author HP
 */
public class Execution {
    private int ExecutionID,Result;
    private List<ExecutionItem> list;

    public Execution() {
    }

    public Execution(int ExecutionID, int Result, List<ExecutionItem> list) {
        this.ExecutionID = ExecutionID;
        this.Result = Result;
        this.list = list;
    }

    public int getExecutionID() {
        return ExecutionID;
    }

    public void setExecutionID(int ExecutionID) {
        this.ExecutionID = ExecutionID;
    }

    public int getResult() {
        return Result;
    }

    public void setResult(int Result) {
        this.Result = Result;
    }

    public List<ExecutionItem> getList() {
        return list;
    }

    public void setList(List<ExecutionItem> list) {
        this.list = list;
    }
    
    
}
