import React from 'react';
import {Navbar,Container,Col} from 'react-bootstrap';
class Footer extends React.Component{
    render(){
        let fullyear = new Date().getFullYear();
        return(
          
            <Navbar fixed = "bottom" bg="dark" variant="dark">
                <Container>
                    <Col lg={12} className="text-center text-muted">
                        <div>{fullyear}-{fullyear+1}, All Rights Reserverd by Roman Volchuk & Mateusz Kalksztejn</div>
                    </Col>
                </Container>
            </Navbar>

        );

    }
}
export default Footer;