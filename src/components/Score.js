import { useState } from "react";
import styled from "styled-components";
import { BsStarFill } from 'react-icons/bs';
const Score = ({score}) => {
    const [hovered, setHovered] = useState(null);
    const [clicked, setClicked] = useState(null);

    const goToFetch = e => {
        setClicked(e.target.id);
        fetch(`http://10.58.3.24:8000/products/1`, {
            //사용할 http 메소드 
            method: 'POST',
            //토큰
            headers: {
                Authorization: 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxfQ.loTjeBWZ9SeXV-BcIxqOtX37AN30ROvsZl0_udeeRJU',
            },
            //서버에 보낼 데이터 (별점)
            body: JSON.stringify({
                rating: e.target.id,
            }),
        });
    };

    return (
        <ReviewBox>
            <StarContainer>
                {[1, 2, 3, 4, 5].map(el => (
                    <BsStarFill style={{
                        fontSize: "40px",
                        color: `${((clicked >= el) | (hovered >= el)) ? 'yellow' : '#dfdfdf'}`
                    }}
                        key={el}
                        onMouseEnter={() => setHovered(el)}
                        onMouseLeave={() => setHovered(null)}
                        onClick={() => setClicked(el)}
                    />
                ))}
            </StarContainer>
        </ReviewBox>
    );
};



const ReviewBox = styled.div`
    font-size: 20px;
    padding: 10px 0;
    position: relative;
    margin-bottom: 5px;
`;

const StarContainer = styled.div`
    text-align: left;
    border: none;
    & > * {
        margin : 5px 0 0 5px;
    }
`;



export default Score;