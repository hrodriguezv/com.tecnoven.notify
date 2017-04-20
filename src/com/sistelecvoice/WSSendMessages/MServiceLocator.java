/**
 * MServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sistelecvoice.WSSendMessages;

public class MServiceLocator extends org.apache.axis.client.Service implements com.sistelecvoice.WSSendMessages.MService {

    public MServiceLocator() {
    }


    public MServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public MServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for mServiceSoap12
    private java.lang.String mServiceSoap12_address = "http://www.sistelecvoice.com/WSSendMessages/mService.asmx";

    public java.lang.String getmServiceSoap12Address() {
        return mServiceSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String mServiceSoap12WSDDServiceName = "mServiceSoap12";

    public java.lang.String getmServiceSoap12WSDDServiceName() {
        return mServiceSoap12WSDDServiceName;
    }

    public void setmServiceSoap12WSDDServiceName(java.lang.String name) {
        mServiceSoap12WSDDServiceName = name;
    }

    public com.sistelecvoice.WSSendMessages.MServiceSoap getmServiceSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(mServiceSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getmServiceSoap12(endpoint);
    }

    public com.sistelecvoice.WSSendMessages.MServiceSoap getmServiceSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sistelecvoice.WSSendMessages.MServiceSoap12Stub _stub = new com.sistelecvoice.WSSendMessages.MServiceSoap12Stub(portAddress, this);
            _stub.setPortName(getmServiceSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setmServiceSoap12EndpointAddress(java.lang.String address) {
        mServiceSoap12_address = address;
    }


    // Use to get a proxy class for mServiceSoap
    private java.lang.String mServiceSoap_address = "http://www.sistelecvoice.com/WSSendMessages/mService.asmx";

    public java.lang.String getmServiceSoapAddress() {
        return mServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String mServiceSoapWSDDServiceName = "mServiceSoap";

    public java.lang.String getmServiceSoapWSDDServiceName() {
        return mServiceSoapWSDDServiceName;
    }

    public void setmServiceSoapWSDDServiceName(java.lang.String name) {
        mServiceSoapWSDDServiceName = name;
    }

    public com.sistelecvoice.WSSendMessages.MServiceSoap getmServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(mServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getmServiceSoap(endpoint);
    }

    public com.sistelecvoice.WSSendMessages.MServiceSoap getmServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sistelecvoice.WSSendMessages.MServiceSoapStub _stub = new com.sistelecvoice.WSSendMessages.MServiceSoapStub(portAddress, this);
            _stub.setPortName(getmServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setmServiceSoapEndpointAddress(java.lang.String address) {
        mServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sistelecvoice.WSSendMessages.MServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sistelecvoice.WSSendMessages.MServiceSoap12Stub _stub = new com.sistelecvoice.WSSendMessages.MServiceSoap12Stub(new java.net.URL(mServiceSoap12_address), this);
                _stub.setPortName(getmServiceSoap12WSDDServiceName());
                return _stub;
            }
            if (com.sistelecvoice.WSSendMessages.MServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sistelecvoice.WSSendMessages.MServiceSoapStub _stub = new com.sistelecvoice.WSSendMessages.MServiceSoapStub(new java.net.URL(mServiceSoap_address), this);
                _stub.setPortName(getmServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("mServiceSoap12".equals(inputPortName)) {
            return getmServiceSoap12();
        }
        else if ("mServiceSoap".equals(inputPortName)) {
            return getmServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://sistelecvoice.com/WSSendMessages/", "mService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://sistelecvoice.com/WSSendMessages/", "mServiceSoap12"));
            ports.add(new javax.xml.namespace.QName("http://sistelecvoice.com/WSSendMessages/", "mServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("mServiceSoap12".equals(portName)) {
            setmServiceSoap12EndpointAddress(address);
        }
        else 
if ("mServiceSoap".equals(portName)) {
            setmServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
