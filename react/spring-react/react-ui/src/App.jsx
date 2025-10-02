import { useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

function App() {
  const [count, setCount] = useState(0)
  const [serverTime, setServerTime] = useState('')

  useEffect(() => {
    const fetchTime = () => {
      fetch('/api/server/time')
        .then(response => response.text())
        .then(time => {
          setServerTime(time);
        });
    };

    fetchTime(); // 초기 실행
    const interval = setInterval(fetchTime, 1000); // 1초마다 실행

    return () => clearInterval(interval); // cleanup
  }, [])

  return (
    <>
      <div>
        <label>current server time - {serverTime}</label>
      </div>
      <div>
        <a href="https://vite.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  )
}

export default App
