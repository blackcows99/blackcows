import React, {useState} from 'react';
import styled from "styled-components";

const TabContent = () => {
    const [fade,setFade] = useState('');
    const [tab, setTab] = useState(false);
    React.useEffect(()=>{
        const a = setTimeout(()=>{setFade('end')},10)
        return () => { setFade(''); clearTimeout(a)}
    },[tab])
    const clickTab = () => {
        setTab(!tab);
    }
    return (
        <div className={`start ${fade}`}>
            <Span onClick={clickTab}>댓글</Span>
            <Line/>
            {tab ? 
            <>
                <div style={{
                    display:"flex",
                    padding:"10px",}}>
                    <strong style={{flexGrow:"1"}}>닉네임</strong>
                    <div style={{flexGrow:"2",textAlign:"center"}}>콘텐츠ddddddddddddddddd</div>
                    <Line/>
                </div>
            </>
            : null}
        </div>
    )
}

const Span = styled.div`
    display: inline-block;
    padding : 8px 15px;
    border:1px solid rgba(108,117,125,0.3);
    border-bottom : none;
    border-radius: 5px 5px 0 5px;
    cursor : pointer ;
    &:hover {
        background-color : rgba(0,0,0,0.7);
        color : white;
    }
`;

const Line = styled.div`
    border-bottom : 1px solid rgba(108,117,125,0.3);
`;

export default TabContent;