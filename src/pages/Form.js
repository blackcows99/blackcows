import React from 'react';
import {Input, Title, Select, Textarea, Image, MyContainer} from '../elements';
import { Score } from '../components';
import styled from 'styled-components';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button } from 'react-bootstrap';
const Form = ({mode, onClick, data}) => {
    const handleOnChangeSelect = (e) => {
        // setSelected(e.target.value);
    }
    return (
        <MyContainer width="60vw">
            <Title text="이미지를 선택해주세요."></Title>
            <div>
                <Input placeholder="파일을 선택해주세요." width="60%"></Input>
                <Button variant="secondary" style={{padding:"10px" ,margin:"0 5px"}}>파일 업로드</Button>{' '}
                <Image src={data?.img_url} ></Image>
                <Title text="평점을 선택해주세요."></Title>
                <Score score={data?.score}/>
            </div>
            <Title text="내용을 입력해주세요." ></Title><br/>
            <Input placeholder="제품명을 입력해주세요." value={data?.device} width="50%"></Input>
            <Select onChange={handleOnChangeSelect} ></Select>
            <Textarea value={data?.content}/>
            <Button variant="secondary" 
            style={{padding:"10px", width:"100%"}}
            onClick={onClick}
            >{mode==="add" ? "작성하기" : "수정하기"}</Button>{' '}
            <SideMenu>{ mode==='add'? "추가 페이지" : "수정 페이지"}</SideMenu>
        </MyContainer>
    )
}

// const Image = styled.div`
//     width: 72.5%;
//     height : 50vh;
//     border: 1px solid black;
//     background-size: cover;
//     background-image: url("${(props) => props.src ? props.src : "https://user-images.githubusercontent.com/75834421/124501682-fb25fd00-ddfc-11eb-93ec-c0330dff399b.jpg"}");
//     margin-bottom : 13px;
// `;

const SideMenu = styled.strong`
    position: absolute;
    top:3%;
    left:-21%;
    padding:10px;
    border-radius:5px;
    font-size : 1.5rem;
`;
export default Form;