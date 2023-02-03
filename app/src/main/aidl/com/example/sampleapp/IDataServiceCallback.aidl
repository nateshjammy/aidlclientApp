// IDataServiceCallback.aidl
package com.example.sampleapp;

import com.example.sampleapp.AppInfo;

interface IDataServiceCallback {

    List<AppInfo> getinstalledAppList();
}