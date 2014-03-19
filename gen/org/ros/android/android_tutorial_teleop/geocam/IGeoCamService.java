/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/iratao/new_workspace/eclipse_ws/nifti_ros_android_app/src/org/ros/android/android_tutorial_teleop/geocam/IGeoCamService.aidl
 */
package org.ros.android.android_tutorial_teleop.geocam;
public interface IGeoCamService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements org.ros.android.android_tutorial_teleop.geocam.IGeoCamService
{
private static final java.lang.String DESCRIPTOR = "org.ros.android.android_tutorial_teleop.geocam.IGeoCamService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an org.ros.android.android_tutorial_teleop.geocam.IGeoCamService interface,
 * generating a proxy if needed.
 */
public static org.ros.android.android_tutorial_teleop.geocam.IGeoCamService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof org.ros.android.android_tutorial_teleop.geocam.IGeoCamService))) {
return ((org.ros.android.android_tutorial_teleop.geocam.IGeoCamService)iin);
}
return new org.ros.android.android_tutorial_teleop.geocam.IGeoCamService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_addToUploadQueue:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.addToUploadQueue(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_addTrackToUploadQueue:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
this.addTrackToUploadQueue(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_clearQueue:
{
data.enforceInterface(DESCRIPTOR);
this.clearQueue();
reply.writeNoException();
return true;
}
case TRANSACTION_isUploading:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isUploading();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getQueueSize:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.getQueueSize();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_lastUploadStatus:
{
data.enforceInterface(DESCRIPTOR);
int _result = this.lastUploadStatus();
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getLocation:
{
data.enforceInterface(DESCRIPTOR);
android.location.Location _result = this.getLocation();
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_increaseLocationUpdateRate:
{
data.enforceInterface(DESCRIPTOR);
this.increaseLocationUpdateRate();
reply.writeNoException();
return true;
}
case TRANSACTION_startRecordingTrack:
{
data.enforceInterface(DESCRIPTOR);
this.startRecordingTrack();
reply.writeNoException();
return true;
}
case TRANSACTION_stopRecordingTrack:
{
data.enforceInterface(DESCRIPTOR);
this.stopRecordingTrack();
reply.writeNoException();
return true;
}
case TRANSACTION_pauseTrack:
{
data.enforceInterface(DESCRIPTOR);
this.pauseTrack();
reply.writeNoException();
return true;
}
case TRANSACTION_resumeTrack:
{
data.enforceInterface(DESCRIPTOR);
this.resumeTrack();
reply.writeNoException();
return true;
}
case TRANSACTION_isRecordingTrack:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isRecordingTrack();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_isTrackPaused:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.isTrackPaused();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_currentTrackId:
{
data.enforceInterface(DESCRIPTOR);
long _result = this.currentTrackId();
reply.writeNoException();
reply.writeLong(_result);
return true;
}
case TRANSACTION_applicationVisible:
{
data.enforceInterface(DESCRIPTOR);
this.applicationVisible();
reply.writeNoException();
return true;
}
case TRANSACTION_applicationInvisible:
{
data.enforceInterface(DESCRIPTOR);
this.applicationInvisible();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements org.ros.android.android_tutorial_teleop.geocam.IGeoCamService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void addToUploadQueue(java.lang.String uri) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(uri);
mRemote.transact(Stub.TRANSACTION_addToUploadQueue, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void addTrackToUploadQueue(long trackId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(trackId);
mRemote.transact(Stub.TRANSACTION_addTrackToUploadQueue, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void clearQueue() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_clearQueue, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public boolean isUploading() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isUploading, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public int getQueueSize() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getQueueSize, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public int lastUploadStatus() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_lastUploadStatus, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public android.location.Location getLocation() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.location.Location _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getLocation, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = android.location.Location.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public void increaseLocationUpdateRate() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_increaseLocationUpdateRate, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void startRecordingTrack() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_startRecordingTrack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void stopRecordingTrack() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stopRecordingTrack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void pauseTrack() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_pauseTrack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void resumeTrack() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_resumeTrack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public boolean isRecordingTrack() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isRecordingTrack, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public boolean isTrackPaused() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_isTrackPaused, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public long currentTrackId() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
long _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_currentTrackId, _data, _reply, 0);
_reply.readException();
_result = _reply.readLong();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public void applicationVisible() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_applicationVisible, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void applicationInvisible() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_applicationInvisible, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_addToUploadQueue = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_addTrackToUploadQueue = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_clearQueue = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_isUploading = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getQueueSize = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_lastUploadStatus = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getLocation = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_increaseLocationUpdateRate = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_startRecordingTrack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_stopRecordingTrack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_pauseTrack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_resumeTrack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_isRecordingTrack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_isTrackPaused = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_currentTrackId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_applicationVisible = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_applicationInvisible = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
}
public void addToUploadQueue(java.lang.String uri) throws android.os.RemoteException;
public void addTrackToUploadQueue(long trackId) throws android.os.RemoteException;
public void clearQueue() throws android.os.RemoteException;
public boolean isUploading() throws android.os.RemoteException;
public int getQueueSize() throws android.os.RemoteException;
public int lastUploadStatus() throws android.os.RemoteException;
public android.location.Location getLocation() throws android.os.RemoteException;
public void increaseLocationUpdateRate() throws android.os.RemoteException;
public void startRecordingTrack() throws android.os.RemoteException;
public void stopRecordingTrack() throws android.os.RemoteException;
public void pauseTrack() throws android.os.RemoteException;
public void resumeTrack() throws android.os.RemoteException;
public boolean isRecordingTrack() throws android.os.RemoteException;
public boolean isTrackPaused() throws android.os.RemoteException;
public long currentTrackId() throws android.os.RemoteException;
public void applicationVisible() throws android.os.RemoteException;
public void applicationInvisible() throws android.os.RemoteException;
}
