# Курсовая работа (вариант №11)
        
Разработать программную систему для имитации процесса обслуживания заданий в вычислительных системах.
Учитываются следующие основные моменты обслуживания заданий: 
<ul>
        <li>генерация нового задания; 
        <li>постановка задания в очередь для ожидания момента освобождения процессора; 
        <li>выборка задания из очереди при освобождении процессора после обслуживания очередного задания.
</ul>
        
Генерация нового задания (процесса) может происходить:
<ul>
        <li>в интерактивном режиме по запросу пользователя
        <li>автоматически системой как случайное событие
</ul>

Каждый процесс характеризуется:
<ul>
        <li>именем;
        <li>длиной рабочей области;
        <li>интервалом непрерывного выполнения;
        <li>причиной прекращения непрерывной работы (обращение к ресурсу или завершение работы);
        <li>приоритетом, если он требуется используемым методом планирования процессора.
</ul>
