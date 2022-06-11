import React from 'react';
import {useNavigate} from 'react-router-dom';
const Main = () => {
    const navigate = useNavigate();
    return (
        <div>
            <button onClick={()=>{navigate('/add')}}>add</button>
            <button onClick={()=>{navigate('/update')}}>update</button>
            <button onClick={()=>{navigate('/detail')}}>detail</button>
        </div>
    )
}

export default Main;