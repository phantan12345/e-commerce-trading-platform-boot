import React from 'react';
import Routers from '../../routers/Routers';
import Footer from '../Footer/Footer';
import Header from '../Header/Header';
import ;
const Layout = () => {
    return  <>
        <Header/>
        <div>
            <Routers />
        </div>
        <Footer/>
   </>
}

export default Layout;
