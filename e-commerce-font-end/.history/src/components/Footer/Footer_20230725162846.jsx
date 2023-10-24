import React from 'react';
import { Col, Container, Row } from "react-bootstrap";
import './footer.css';
const Footer = () => {
    return <footer className="footer">
        <Container>
            <Row>
                <Col lg='4'>
                <div className='logo'>
                    <img src={logo} alt=''/>
                    <div>
                        <h1>HT Store</h1>
                        <p>Welcome to HT Store</p>
                    </div>
                </div>
                </Col>
                <Col lg='3'></Col>
                <Col lg='2'></Col>
                <Col lg='3'></Col>
            </Row>
        </Container>
    </footer>
}

export default Footer;