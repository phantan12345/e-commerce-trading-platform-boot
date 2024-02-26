import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useSelector } from 'react-redux';


const ProtectedRoute =()=>{
    const {currentUser}= useSelector(a);
    return currentUser ? <Outlet/> : <Navigate to='/login'/>
}

export default ProtectedRoute;