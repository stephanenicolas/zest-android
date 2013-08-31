IP=`./genymotion-detect-ip.sh`

adb root
adb connect $IP:5555
adb shell reboot -p &
