import React from 'react';
import { Navigate } from 'react-router-dom';
import useAuth from '../custom-hooks/useAuth';


const ProtectedRoute =()=>{
    const {currentUser}= useAuth();
    return currentUser ? Outle : <Navigate to='/login'/>
}

export default ProtectedRoute;