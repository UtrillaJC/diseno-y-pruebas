@echo off

rem purge-all.cmd
rem 
rem Copyright (C) 2014 Universidad de Sevilla
rem 
rem The use of this project is hereby constrained to the conditions of the 
rem TDG Licence, a copy of which you may download from 
rem http://www.tdg-seville.info/License.html

for /d %%w in (*Workspace*) do (
	cd "%%w"
	copy ..\purge-workspace.template purge-workspace.cmd 2>&1 1> nul
	if exist purge-workspace.log (
		del purge-workspace.log
	)
	call purge-workspace.cmd
	cd ..
)

pause
