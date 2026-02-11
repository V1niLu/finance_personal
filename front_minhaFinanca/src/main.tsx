import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { MainPage } from './pages/main_page.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <MainPage />
  </StrictMode>,
)
