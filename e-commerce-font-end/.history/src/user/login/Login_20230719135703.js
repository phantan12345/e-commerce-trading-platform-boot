import React from "react";
import {useForm} from 'react-hook-form'

const Login = ()=> {
    const {
        handleSubmit,
        register,
        formState:{ errors },
    }= useForm();

    const onSubmit = (data) => {
        console.log(data);
    }
  return (
    <div 
  )  
}