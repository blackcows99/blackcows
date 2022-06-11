import styled from "styled-components";
const Select = ({onChange}) => {
    return (
        <SelectBox onChange={onChange} >
            <option value="0" >컴퓨터</option>   {/* value 값이 서버로 넘어감 */}
            <option value="1" >노트북</option>
            <option value="2" >웨어러블</option>
            <option value="3" >가전제품</option>
            <option value="4" >기타</option>
        </SelectBox>
    )
}

const SelectBox = styled.select`
    margin-left : 10px;
    outline : none;
    border: 2px solid rgba(108,117,125,0.3);
    border-radius: 5px;
    height : 45px;
    transition : .5s;
    width : 20vw;
    &:focus {
        border: 2px solid rgba(108,117,125,0.8);
    }
`;


export default Select;
