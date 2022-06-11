import styled from "styled-components";
const Textarea = () => {
    return (
        <div>
        <TextArea placeholder="내용을 입력해주세요."></TextArea>
        </div>
    )
}

const TextArea = styled.textarea`
    width: 100%;
    height: 300px;
    outline : none;
    border: 2px solid rgba(108,117,125,0.3);
    border-radius: 5px;
    transition : .5s;
    padding : 10px;
    margin-bottom: 10px;
    &:focus {
        border: 2px solid rgba(108,117,125,0.8);
    }
`;

export default Textarea;