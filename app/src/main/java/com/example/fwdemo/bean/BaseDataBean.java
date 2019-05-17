package com.example.fwdemo.bean;

import java.io.Serializable;

/**
 * @author Alan
 * @date 2018/7/19
 */
public class BaseDataBean implements Serializable {
   private int result;
   private String error;

   public int getResult() {
      return result;
   }

   public void setResult(int result) {
      this.result = result;
   }

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }
}
