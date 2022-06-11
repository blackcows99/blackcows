import React from 'react';
import styled from 'styled-components';
import { FaHome } from "react-icons/fa";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const Header = () => {
    const navigate = useNavigate();
    const signIn = () => {
        navigate('/login');
    }
    const signOut = () => {
        navigate('/login');
    }
    const signUp = () => {
        navigate('/sign_up')
    }
    return (
        <Container>
            <FaHome onClick={()=>{navigate('/')}}
             style={{
                marginLeft:"20px", 
                fontSize:"30px",
                cursor:"pointer",
                }} />
            <BtnBox>
            <strong>잘생긴님 안녕하세요.</strong>
                <Button onClick={signIn}variant="secondary">로그인</Button>{' '}
                <Button onClick={signOut} variant="secondary">로그아웃</Button>{' '}
                <Button onClick={signUp} variant="secondary">회원가입</Button>{' '}
            </BtnBox>
        </Container>
    )
}

const Container = styled.div`
    border:1px solid black;
    display : flex;
    justify-content: space-between;
    align-items : center;
    height : 70px;
`;

const BtnBox = styled.div`
    display : flex;
    align-items : center;
    & > * {
        margin-right : 10px;
    }
`;
export default Header;