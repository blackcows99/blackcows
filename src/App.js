import logo from './logo.svg';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import Main from './pages/Main';
import Detail from './pages/Detail';
import Update from './pages/Update';
import Add from './pages/Add';
function App() {
  return (
    <div className="App">
      <Routes>
        <Route path='/' element={<Add />} />
        <Route path='/detail' element={<Detail />} />
        <Route path='/add' element={<Add />} />
        <Route path='/update' element={<Update />} />
      </Routes>

    </div>
  );
}

export default App;
