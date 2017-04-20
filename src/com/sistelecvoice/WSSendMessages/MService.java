/**
 * MService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sistelecvoice.WSSendMessages;

public interface MService extends javax.xml.rpc.Service {
    public java.lang.String getmServiceSoap12Address();

    public com.sistelecvoice.WSSendMessages.MServiceSoap getmServiceSoap12() throws javax.xml.rpc.ServiceException;

    public com.sistelecvoice.WSSendMessages.MServiceSoap getmServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getmServiceSoapAddress();

    public com.sistelecvoice.WSSendMessages.MServiceSoap getmServiceSoap() throws javax.xml.rpc.ServiceException;

    public com.sistelecvoice.WSSendMessages.MServiceSoap getmServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
