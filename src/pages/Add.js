import React from 'react';
import Form from './Form';
const Add = () => {
    const handleAdd = (object) => {
        // dispatch ~~  db에 저장할 수 있는 로직
      };
      return (
        <div>
          <Form mode="add" onClick={handleAdd} />
        </div>
      );
}


export default Add;