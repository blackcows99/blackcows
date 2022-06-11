import React, {useState} from 'react';
import styled from 'styled-components';
import { MyContainer, Image } from '../elements';
import { TabContent } from '../components';
import { BsStarFill } from 'react-icons/bs';
const Detail = ({ data }) => {
    return (
        <MyContainer width="60vw">
            <TitleBox>
                <strong>닉네임</strong>
                <div>시간</div>
            </TitleBox>
            <ContentBox>
                <Image src={data?.img_url} width="50%"></Image>
                <div style={{ padding: "10px" }}>
                    <p><strong style={{ fontSize: "1.1rem" }}>제품명 : 어쩌구</strong></p>
                    <p>게시글 내용</p>
                </div>
            </ContentBox>
            <Center>
                <div>
                    <Category>카테고리명</Category>
                    {[1, 2, 3, 4, 5].map(el => (
                        <BsStarFill
                            key={el}
                            style={{
                                fontSize: "30px",
                                color: `${el <= data?.score ? "yellow" : "#dfdfdf"}`
                            }}
                        />
                    ))}
                </div>
            </Center>
            <TabContent/>
            <SideMenu>상세 페이지</SideMenu>
        </MyContainer>
    )
}



const TitleBox = styled.div`
    display: flex;
    justify-content : space-between;
    padding : 8px;
`;

const ContentBox = styled.div`
    border:1px solid rgba(108,117,125,0.3);
    display:flex;
`;

const Center = styled.div`
    display:flex;
    margin : 13px 0;
    justify-content : space-between;
`;

const Category = styled.span`
    background-color: rgba(108,117,125,0.3);
    color: black;
    padding : 8px;
    border-radius: 1.2rem;
    font-size : 0.8rem;
    margin-right:5px;
`;

const SideMenu = styled.strong`
    position: absolute;
    top:3%;
    left:-21%;
    padding:10px;
    border-radius:5px;
    font-size : 1.5rem;
`;

export default Detail;