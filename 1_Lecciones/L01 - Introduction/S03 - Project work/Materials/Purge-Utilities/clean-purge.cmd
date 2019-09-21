@echo off

rem purge-all.cmd
rem 
rem Copyright (C) 2014 Universidad de Sevilla
rem 
rem The use of this project is hereby constrained to the conditions of the 
rem TDG Licence, a copy of which you may download from 
rem http://www.tdg-seville.info/License.html

for /d %%w in (*Workspace*) do (
	echo Cleaning purge from "%%w"...
	del "%%w"\purge-workspace.cmd
	del "%%w"\purge-workspace.log
)

pause