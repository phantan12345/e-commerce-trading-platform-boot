import React from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import './services.css'
import serviceData from '../assets/data/serviceData.js';
const Services = () => {
    return <section className="services">
        <Container>
            <Row>
            {
                serviceData.map((item,index)=>(
                    <Col lg-about='3' md='4' key={index}>
                    <DeviceMotionEvent.div className="service__item" style={{background: `${item.bg}`}}>
                        <span><i class={item.icon}></i></span>
                        <div>
                            <h3>{item.title}</h3>
                            <p>{item.subtitle}</p>
                        </div>
                    </DeviceMotionEvent.div>
                </Col>
                ))
            }
                
            </Row>
        </Container>
    </section>
}

export default Services;
