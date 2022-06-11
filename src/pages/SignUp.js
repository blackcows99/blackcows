import React from 'react';
import { MyContainer, Input, CustomButton, Image } from '../elements';


const SignUp = () => {
    return (
        <>
        <Image width="70vw" height="30vh"></Image>
        <MyContainer width="40vw">
            <h3 style={{ textAlign: "center" }}>회원가입</h3>
            <label>아이디</label><br/>
            <Input placeholder="아이디를 입력하세요." width="27vw" />
            <CustomButton text="중복확인"></CustomButton>
            <label>비밀번호</label>
            <Input placeholder="비밀번호를 입력하세요." />
            <label>비밀번호 확인</label>
            <Input placeholder="비밀번호를 입력하세요." />
            <label>닉네임</label>
            <Input placeholder="닉네임 입력하세요." />
            <CustomButton text="회원가입" is_block></CustomButton>
        </MyContainer>
    </>
    );
}

export default SignUp;